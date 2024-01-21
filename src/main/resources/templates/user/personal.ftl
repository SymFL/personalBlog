<#include "../import/viewTop.ftl">
<#include "../import/userBar.ftl">

<div class="panel col-sm-10" style="padding-left: 0;padding-right: 0;">
    <div class="panel-heading">
        <i class="icon icon-user"></i> 个人中心
    </div>
    <div class="panel-body">
        <h4>
            <span class="label"><i class="icon icon-time"></i> 注册时间:</span>
            <span class="label label-success">${(registerTime)?string("yyyy-MM-dd HH:mm:ss")}</span>
        </h4>
        <h4>
            <span class="label"><i class="icon icon-user"></i> 用户名:</span>
            <span class="label label-success">${name}</span>
        </h4>
        <h4>
            <span class="label"><i class="icon icon-leaf"></i> 状态:</span>
            <span class="label label-success">正常</span>
        </h4>
    </div>
</div>

<#include "../import/viewBottom.ftl">