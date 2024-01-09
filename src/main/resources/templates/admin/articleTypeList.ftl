<#-- 文章类型页面 -->
<#include "../import/adminTop.ftl">

<#--  添加标签面板  -->
<div class="panel">
    <div class="panel-body">
        <button onclick="addOrUpdateArticleType()" class="btn btn-lg btn-success" type="button">添加文章标签</button>
    </div>
</div>

<#--  列表面板  -->
<#if typeList?? && typeList?size gt 0>
    <div class="panel">
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>排序</th>
                    <th>文章类型</th>
                    <th>文章数</th>
                    <th>添加时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list typeList as type>
                    <tr>
                        <td>${(type.articleTypeSort)!}</td>
                        <td>${(type.articleTypeName)!}</td>
                        <td>${(type.articleCount)!}</td>
                        <td>${(type.articleTypeAddTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>

                            <button type="button"
                                    onclick="addOrUpdateArticleType('${(type.articleTypeId)!}','${(type.articleTypeName)!}','${(type.articleTypeSort)!}')"
                                    class="btn btn-default">
                                <i class="icon icon-spin icon-cog"></i>修改
                            </button>
                            <button type="button"
                                    onclick="delArticleType('${(type.articleTypeId)!}')"
                                    class="btn btn-default">
                                <i class="icon icon-remove-sign"></i>删除
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
<#else >
    <div class="panel">
        <div class="panel-body">
            <h4><i class="icon icon-info-sign"></i>暂无数据</h4>
        </div>
    </div>
</#if>

<#-- 修改/添加标签弹出框 -->
<div class="modal fade" id="articleTypeUpdateModal">
    <div class="modal-dialog">
        <form class="form-horizontal" action="/csk2024/user/update" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改/添加文章标签</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="articleTypeIdUpdate">
                    <div class="form-group">
                        <label for="articleTypeNameUpdate" class="col-sm-2">文章类型:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="articleTypeNameUpdate" placeholder="类型名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="articleTypeSortUpdate" class="col-sm-2">排序:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="number" class="form-control" id="articleTypeSortUpdate" placeholder="类型排序">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="addOrUpdateArticleTypeAction()">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>

    <#--  文章类型修改/添加提交  -->

    function addOrUpdateArticleTypeAction() {
        var articleTypeId = $('#articleTypeIdUpdate').val();
        var articleTypeName = $('#articleTypeNameUpdate').val();
        var articleTypeSort = $('#articleTypeSortUpdate').val();


        if (!checkNotNull(articleTypeName)) {
            zuiMsg('提示信息：请输入文章类型');
            return;
        }

        if (!checkNotNull(articleTypeSort)) {
            zuiMsg('提示信息：请输入文章类型排序');
            return;
        }

        $.post("/csk2024/article/type/addOrUpdate",
            {
                articleTypeId: articleTypeId,
                articleTypeName: articleTypeName,
                articleTypeSort: articleTypeSort
            },
            function (data) {
                if (data.code === 200) {
                    alert(data.message);
                    location.reload();
                    return;
                }
                zuiMsg('提示信息：' + data.message);
            })
    }

    <#--  文章类型修改/增加弹出框  -->

    function addOrUpdateArticleType(articleTypeId, articleTypeName, articleTypeSort) {
        $('#articleTypeUpdateModal').modal('toggle', 'center');
        $('#articleTypeIdUpdate').val(articleTypeId);
        $('#articleTypeNameUpdate').val(articleTypeName);
        $('#articleTypeSortUpdate').val(articleTypeSort);
    }

    <#--  删除类型  -->

    function delArticleType(articleTypeId) {
        if (confirm("确定要删除吗？")) {
            if (!checkNotNull(articleTypeId)) {
                zuiMsg('提示消息：出现错误，请刷新页面');
                return;
            }
            $.post("/csk2024/article/type/del", {articleTypeId: articleTypeId}, function (data) {
                if (data.code === 200) {
                    alert(data.message);
                    location.reload();
                    return;
                }
                zuiMsg('提示消息：' + data.message);
            });
        }
    }


</script>

<#include "../import/adminBottom.ftl">