<#-- 用户列表页面 -->
<#include "../import/adminTop.ftl">

<#--  查询面板  -->
<div class="panel">
    <div class="panel-body">
        <form class="form-horizontal" action="/csk2024/user/list" method="get">
            <div class="form-group">
                <label for="userName" class="col-sm-1">用户名:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="userName" id="userName"
                           placeholder="用户名"
                           value="<#if (userName?? && userName?length>0)>${userName!}<#else></#if>">
                </div>
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">查询</button>
                </div>
                <div class="col-sm-1">
                    <a href="/csk2024/user/list" class="btn btn-default">查询全部</a>
                </div>
            </div>
        </form>
    </div>
</div>

<#--  列表面板  -->
<#if userPage?? && userPage.list?size gt 0>
    <h4><i class="icon icon-info-sign"></i>被冻结的用户无法登陆</h4>
    <div class="panel">
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>注册时间</th>
                    <th>是否冻结</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list userPage.list as user>
                    <tr>
                        <td>${(user.userName)!}</td>
                        <td>${(user.userRegisterTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>
                            <#if (user.userFrozen)?? && (user.userFrozen) == 1>
                                <span class="label label-danger">冻结</span>
                            <#else>
                                <span class="label label-success">正常</span>
                            </#if>
                        </td>
                        <td>

                            <button type="button"
                                    onclick="userUpdate('${(user.userId)!}','${(user.userName)!}','${(user.userFrozen)!}')"
                                    class="btn btn-default">
                                <i class="icon icon-spin icon-cog"></i>修改
                            </button>
                            <button type="button"
                                    onclick="delUser('${(user.userId)!}','${(userPage.pageNumber)!}','${(userPage.list)?size}')"
                                    class="btn btn-default">
                                <i class="icon icon-remove-sign"></i>删除
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="panel-footer">
            <ul class="pager" id="myPager" data-ride="pager"
                data-page=${userPage.pageNumber} data-rec-per-page=${userPage.pageSize} data-rec-total=${userPage.total}
                data-elements="prev_icon,nav,next_icon,page_of_total_text,total_text"
                data-link-creator="/csk2024/user/list?pageNumber={page}<#if (userName?? && userName?length>0)>&userName=${userName!}</#if>"></ul>
        </div>
    </div>
<#else >
    <div class="panel">
        <div class="panel-body">
            <h4><i class="icon icon-info-sign"></i>暂无数据</h4>
        </div>
    </div>
</#if>

<#-- 修改用户弹出框 -->
<div class="modal fade" id="userUpdateModal">
    <div class="modal-dialog">
        <form class="form-horizontal" action="/csk2024/user/update" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改用户</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="userIdUpdate">
                    <div class="form-group">
                        <label for="userNameUpdate" class="col-sm-2">用户名:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" disabled="disabled" class="form-control" id="userNameUpdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userPasswordUpdate" class="col-sm-2">用户密码:</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="password" class="form-control" id="userPasswordUpdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">是否冻结:</label>
                        <div class="col-sm-2">
                            <label>
                                <input type="radio" name="userFrozen" value="0"> 正常
                            </label>
                            <label>
                                <input type="radio" name="userFrozen" value="1"> 冻结
                            </label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="userUpdateAction()">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    <#--  用户修改提交  -->
    function userUpdateAction() {
        var userId = $('#userIdUpdate').val();
        var userName = $('#userNameUpdate').val();
        var userPassword = $('#userPasswordUpdate').val();
        var userFrozen = $("input[name='userFrozen']:checked").val();



        if (!checkNotNull(userId)) {
            zuiMsg('提示信息：发生错误，请刷新');
            return;
        }
        console.log(userId);
        console.log(userPassword);
        console.log(userFrozen);

        $.post("/csk2024/user/update",
            {
                userId: userId,
                userName: userName,
                userPassword: sha256(userPassword),
                userFrozen: userFrozen
            },
            function (data) {
                if (data.code === 200) {
                    alert(data.message);
                    location.reload();
                    return;
                }
                zuiMsg('提示信息' + data.message);
            })
    }

    <#--  用户修改弹出框  -->
    function userUpdate(userId, userName, userFrozen) {
        $('#userUpdateModal').modal('toggle', 'center');
        $('#userIdUpdate').val(userId);
        $('#userNameUpdate').val(userName);
        $("input[name='userFrozen'][value='" + userFrozen + "']").prop("checked", true);
    }

    <#--  删除用户  -->
    function delUser(userId, pageNumber, pageSize) {
        var number = parseInt(pageNumber);
        var size = parseInt(pageSize);
        if (confirm("确定要删除吗？")) {
            if (!checkNotNull(userId)) {
                zuiMsg('提示消息：出现错误，请刷新页面');
                return;
            }
            $.post("/csk2024/user/del", {userId: userId}, function (data) {
                if (data.code === 200) {
                    alert(data.message);
                    if (number === 1 && size === 1) {
                        window.location.href = "/csk2024/user/list";
                        return;
                    } else if (number > 1 && size === 1) {
                        var url = new URL(window.location.href);
                        var params = url.searchParams;
                        params.set('pageNumber', (number - 1).toString());
                        url.search = params.toString();
                        window.location.href = url.toString();
                        return;
                    }
                    location.reload();
                    return;
                }
                zuiMsg('提示消息：' + data.message);
            });
        }
    }


</script>

<#include "../import/adminBottom.ftl">