<#include "../import/top.ftl">
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">管理员登录</div>
        <div class="panel-body" style="height: 380px;">
            <form class="form-horizontal" action="/csk2024/adminLogin" method="post" id="loginUserForm">
                <div class="form-group">
                    <label for="adminName" class="col-sm-2">用户名：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="adminName" maxlength="50" name="adminName"
                               placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="adminPassword" class="col-sm-2">密码：</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="adminPassword" maxlength="50" name="adminPassword"
                               placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="verifyCode" class="col-sm-2">验证码：</label>
                    <div class="col-sm-10">
                        <div class="col-sm-10 col-xs-9" style="padding-left:0; padding-right:0;">
                            <input type="text" class="form-control" id="verifyCode" maxlength="4"
                                   name="verifyCode" placeholder="验证码">
                        </div>
                        <div class="col-sm-2 col-xs-3" style="padding-left:0; padding-right:0;">
                            <img class="img-thumbnail" src="/getCaptcha" id="check_code_img"
                                 onclick="javascript:this.src='/getCaptcha?' + new Date().getTime();"
                                 style="cursor: pointer; height: 32px; width: 100px;" title="点击刷新验证码"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">
                    </div>
                    <div class="col-sm-2">
                        <button type="button" onclick="submitForm()" class="btn btn-default">
                            <i class="icon-signin"></i> 登录
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    function submitForm() {
        let adminName = $("#adminName").val();
        let adminPassword = sha256($("#adminPassword").val());
        let verifyCode = $("#verifyCode").val();

        if (!checkNotNull(adminName) || !checkNotNull(adminPassword)) {
            zuiMsg("请输入登录信息");
            return;
        }
        if (!checkNotNull(verifyCode) || verifyCode.length != 4) {
            zuiMsg("验证码不正确");
            return;
        }
        $.post("/csk2024/adminLogin", {
                adminName: adminName,
                adminPassword: adminPassword,
                verifyCode: verifyCode
            },
            function (data) {
                if (data.code === 200) {
                    window.location.href = "/csk2024/";
                    return;
                }
                zuiMsg(data.message);
                $("#check_code_img").click();
            });
    }

</script>
<#include "../import/bottom.ftl">