<#-- 友链列表页面 -->
<#include "../import/adminTop.ftl">
<#--  列表面板  -->
<div class="panel">
    <div class="panel-heading">
        <button type="button"
                onclick="linkAddOrUpdate()"
                class="btn btn-primary">添加友链
        </button>
    </div>
    <h3>友链数：${(links?size)!}</h3>
    <#if links?? && links?size gt 0>
    <div class="panel-body">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>排序</th>
                <th>友链名</th>
                <th>友链Logo</th>
                <th>添加时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#list links as link>
                <tr>
                    <td>${(link.linkSort)!}</td>
                    <td>${(link.linkTitle)!}</td>
                    <td>
                        <img class="thumbnail img-thumbnail" src="${link.linkLogoUrl}" alt="友链Logo"/>
                    </td>
                    <td>${(link.linkAddTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>
                        <a href="${(link.linkUrl)!}" target="_blank" class="btn">
                            <i class="icon-eye-open"></i> 查看</a>
                        <button type="button"
                                onclick="linkAddOrUpdate('${(link.linkId)!}',
                                        '${(link.linkUrl)!}',
                                        '${(link.linkLogoUrl)!}',
                                        '${(link.linkTitle)!}',
                                        '${(link.linkSort)!}')"
                                class="btn btn-default">
                            <i class="icon icon-spin icon-cog"></i>修改
                        </button>
                        <button type="button"
                                onclick="linkDelete('${link.linkId}')"
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

<#-- 修改/添加友链弹出框 -->
<div class="modal fade" id="linkUpdateModal">
    <div class="modal-dialog">
        <form class="form-horizontal" action="/csk2024/link/addOrUpdate" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改/添加友链</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="linkIdUpdate">
                    <div class="form-group">
                        <label for="linkNameUpdate" class="col-sm-2">友链名称:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="linkTitleUpdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkUrlUpdate" class="col-sm-2">友链地址:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="linkUrlUpdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkLogoUrlUpdate" class="col-sm-2">友链Logo:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="linkLogoUrlUpdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="linkSortUpdate" class="col-sm-2">排序:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="number" class="form-control" id="linkSortUpdate">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="linkAddOrUpdateAction()">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    <#--  添加/修改友链提交  -->

    function linkAddOrUpdateAction() {
        let linkId = $('#linkIdUpdate').val();
        let linkUrl = $('#linkUrlUpdate').val();
        let linkLogoUrl = $('#linkLogoUrlUpdate').val();
        let linkTitle = $('#linkTitleUpdate').val();
        let linkSort = $('#linkSortUpdate').val();

        if (!checkNotNull(linkUrl) || !checkNotNull(linkLogoUrl) || !checkNotNull(linkTitle) || !checkNotNull(linkSort)) {
            zuiMsg("提示消息：数据错误!")
        }

        $.post("/csk2024/link/addOrUpdate",
            {
                linkId: linkId,
                linkUrl: linkUrl,
                linkLogoUrl: linkLogoUrl,
                linkTitle: linkTitle,
                linkSort: linkSort,
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

    <#--  唤起添加/修改友链弹出框  -->

    function linkAddOrUpdate(linkId, linkUrl, linkLogoUrl, linkTitle, linkSort) {
        $('#linkUpdateModal').modal('toggle', 'center');
        $('#linkIdUpdate').val(linkId);
        $('#linkUrlUpdate').val(linkUrl);
        $('#linkLogoUrlUpdate').val(linkLogoUrl);
        $('#linkTitleUpdate').val(linkTitle);
        $('#linkSortUpdate').val(linkSort);
    }

    <#--    删除友链    -->

    function linkDelete(linkId) {
        if (confirm("确定要删除该友链吗？")) {
            if (!checkNotNull(linkId)) {
                zuiMsg("提示消息：出现异常，请刷新页面");
                return;
            }

            $.post("/csk2024/link/delete",
                {
                    linkId: linkId
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
    }


</script>

<#include "../import/adminBottom.ftl">