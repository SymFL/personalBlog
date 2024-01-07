<#include "../import/top.ftl">

<div class="panel">
    <div class="panel-body">
        <form class="form-horizontal" action="/csk2024/user/list" method="get">
            <div class="form-group">
                <label for="userName" class="col-sm-1">用户名:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="用户名">
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

                            <button type="button" class="btn btn-default"><i class="icon icon-spin icon-cog"></i>修改</button>
                            <button type="button" onclick="delUser('${(user.userId)!}')" class="btn btn-default">
                                <i class="icon icon-remove-sign"></i>删除</button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="panel-footer">
            <ul class="pager" id="myPager" data-ride="pager"
                data-page=${userPage.pageNumber} data-rec-per-page=${userPage.pageSize} data-rec-total=${userPage.total}
                data-elements="prev_icon,nav,next_icon,page_of_total_text,page_size_text"
                data-link-creator="/csk2024/user/list?pageNumber={page}"></ul>
        </div>
    </div>
<#else >
    <div class="panel">
        <div class="panel-body">
            <h4><i class="icon icon-info-sign"></i>暂无数据</h4>
        </div>
    </div>
</#if>

<script>
    function delUser(userId) {
        if (confirm("确定要删除吗？")) {
            if (!checkNotNull(userId)) {
                new $.zui.Messager('提示消息：出现错误，请刷新页面', {
                    type: 'warning' // 定义颜色主题
                }).show();
                return;
            }
            $.post("/csk2024/user/del", {userId: userId}, function (data) {
                if (data.code === 200) {
                    alert(data.message);
                    location.reload();
                    return;
                }
                new $.zui.Messager('提示消息：' + data.message, {
                    type: 'warning' // 定义颜色主题
                }).show();
            });
        }
    }

    function checkNotNull(value) {
        if (value === null || value === undefined) {
            throw new Error('Value cannot be null or undefined');
        }
        return value;
    }
</script>

<#include "../import/bottom.ftl">