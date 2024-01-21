<#--用户注册页面-->
<#include "../import/viewTop.ftl">
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">注册</div>
        <div class="panel-body" style="height: 380px;">
            <form class="form-horizontal" action="/user/register" method="post" id="userRegisterForm">
                <div class="form-group">
                    <label for="userName" class="col-sm-2">用户名：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="userName" maxlength="18" name="adminName"
                               placeholder="只允许输入字母和数字，长度为5-20">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPassword" class="col-sm-2">密码：</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="userPassword" maxlength="50" name="adminPassword"
                               placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPasswordAgain" class="col-sm-2">确认密码：</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="userPasswordAgain" maxlength="50" name="userPasswordAgain"
                               placeholder="确认密码">
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
                        <button type="button" onclick="submitUserRegisterForm()" class="btn btn-default">
                            <i class="icon-signin"></i> 注册
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function submitUserRegisterForm() {
        let userName = $("#userName").val();
        let userPassword = $("#userPassword").val();
        let userPasswordAgain = $("#userPasswordAgain").val()
        let verifyCode = $("#verifyCode").val();

        if (!checkNotNull(userName))
        {
            zuiMsg("用户名不能为空!");
            return;
        }

        if ((userName.length < 5) || (userName.length > 20))
        {
            zuiMsg("用户名必须为5 ~ 20位");
            return;
        }

        let arr = ["&", "\\", "/", "*", ">", "<", "@", "!"];
        for (let i = 0; i < arr.length; i++)
        {
            for (let j = 0; j < userName.length; j++)
            {
                if (arr[i] === userName.charAt(j))
                {
                    zuiMsg("用户名不允许包含特殊字符!")
                    return;
                }
            }
        }

        if (!checkNotNull(userPassword)) {
            zuiMsg("密码不能为空!");
            return;
        }

        if(userPasswordAgain !== userPassword){
            zuiMsg("两次密码不一致!");
            return;
        }

        if (!checkNotNull(verifyCode) || verifyCode.length !== 4) {
            zuiMsg("验证码不正确!");
            return;
        }

        $.post("/user/registerAction", {
                userName: userName,
                userPassword: sha256(userPassword),
                verifyCode: verifyCode
            },
            function (data) {
                if (data.code === 200) {
                    window.location.href = "/user/login";
                    return;
                }
                zuiMsg(data.message);
                $("#check_code_img").click();
            });
    }

</script>
<#include "../import/viewBottom.ftl">