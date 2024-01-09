<#-- 文章标签页面 -->
<#include "../import/adminTop.ftl">

<#--  列表页面  -->
<div class="panel">
    <div class="panel-heading">
        <form class="form-inline" id="articleTagAdd">
            <div class="form-group">
                <label for="articleTagNameAdd">文章标签：</label>
                <input type="text" class="form-control" id="articleTagNameAdd" placeholder="标签名">
            </div>
            <button type="button" onclick="articleTagAdd()" class="btn btn-primary">添加</button>
        </form>
    </div>

    <div class="panel-body container">
        <#if articleTags?? && articleTags?size gt 0>
            <#list articleTags as articleTag>
                <div class="col-sm-2">
                    <h2>
                        <span class="label"
                              onclick="articleTagUpdate('${(articleTag.articleTagId)!}','${(articleTag.articleTagName)!}')"
                        >${articleTag.articleTagName}</span>
                        <i class="icon icon-spin icon-cog" data-toggle="tooltip" data-placement="bottom" title="修改"
                           onclick="articleTagUpdate('${(articleTag.articleTagId)!}','${(articleTag.articleTagName)!}')"
                        ></i>
                        <i class="icon icon-times" data-toggle="tooltip" data-placement="bottom" title="删除"
                           onclick="articleTagDel('${(articleTag.articleTagId)!}')"
                        ></i>
                    </h2>
                </div>

            </#list>
        </#if>
    </div>

</div>

<#-- 修改标签弹出框 -->
<div class="modal fade" id="articleTagUpdateModal">
    <div class="modal-dialog">
        <form class="form-horizontal" action="/csk2024/user/update" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改文章标签</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="articleTagIdUpdate">
                    <div class="form-group">
                        <label for="articleTagNameUpdate" class="col-sm-2">文章标签:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="articleTagNameUpdate" placeholder="标签名称">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="articleTagUpdateAction()">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    <#--  标签删除  -->
    function articleTagDel(articleTagId) {
        if(confirm("是否确定要删除该标签")){
            if (!checkNotNull(articleTagId)) {
                zuiMsg('提示消息：标签 id 不允许为空');
                return;
            }
            $.post("/csk2024/article/tag/delete",
                {
                    articleTagId: articleTagId
                },
                function (data){
                    if(data.code === 200){
                        alert(data.message);
                        location.reload();
                        return;
                    }
                    zuiMsg("提示消息：" + data.message);
                });
        }
    }


    <#--  标签修改提交  -->

    function articleTagUpdateAction() {
        var articleTagId = $('#articleTagIdUpdate').val();
        var articleTagName = $('#articleTagNameUpdate').val();

        if (!checkNotNull(articleTagId)) {
            zuiMsg("标签 id 为空，请刷新页面");
            return;
        }

        $.post("/csk2024/article/tag/addOrUpdate",
            {
                articleTagId: articleTagId,
                articleTagName: articleTagName,
            },
            function (date) {
                if (date.code === 200) {
                    alert(date.message);
                    location.reload();
                    return;
                }
                zuiMsg(date.message);
            }
        );
    }

    <#--  标签修改  -->

    function articleTagUpdate(articleTagId, articleTagName, articleTagAddTime) {
        $('#articleTagUpdateModal').modal('toggle', 'center');
        $('#articleTagIdUpdate').val(articleTagId);
        $('#articleTagNameUpdate').val(articleTagName);
    }


    <#--  标签添加  -->

    function articleTagAdd() {
        var articleTagName = $('#articleTagNameAdd').val();

        console.log(articleTagName);

        if (!checkNotNull(articleTagName)) {
            zuiMsg("标签名不能为空！")
            return;
        }

        $.post("/csk2024/article/tag/addOrUpdate",
            {
                articleTagName: articleTagName
            },
            function (date) {
                if (date.code === 200) {
                    alert(date.message);
                    location.reload();
                    return;
                }
                zuiMsg("提示消息：" + date.message);
            }
        );
    }

    <#--  修改标签添加表单回车默认提交  -->
    $('#articleTagAdd').on('keydown', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            articleTagAdd();
        }
    });

</script>

<#include "../import/adminBottom.ftl">