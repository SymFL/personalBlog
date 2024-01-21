<#--  用户端通用头文件 -->
<#include "./top.ftl">
<div class="container">
    <#--  网站站头  -->
    <nav class="navbar" role="navigation" style="padding-top: 10px;margin-bottom: 5px;">
        <div class="container-fluid">
            <!-- 导航头部 -->
            <div class="navbar-header">
                <!-- 移动设备上的导航切换按钮 -->
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-collapse-example">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- 品牌名称或logo -->
                <a class="navbar-brand" href="/index" style="font-size: 40px;">分享家</a>
            </div>
            <!-- 导航项目 -->
            <div class="collapse navbar-collapse navbar-collapse-example" style="font-size: 25px;">
                <button type="button" class="btn btn-default navbar-right btn-danger"><i class="icon icon-android"></i>
                    App下载
                </button>
            </div><!-- END .navbar-collapse -->
        </div>
    </nav>
</div>
<#--导航-->
<nav class="navbar navbar-inverse" role="navigation" style="padding: 5px;">
    <div class="container-fluid" style="padding-left: 332px;padding-right: 332px;">
        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example" style="font-size: 20px;">
            <ul class="nav navbar-nav">
                <li class="dropdown"><a href="/index">首页</a></li>
                <li><a href="/index?aType=1">技术分享</a></li>
                <li><a href="/index?aType=2">游戏推荐</a></li>
                <li><a href="/index?aType=3">软件推荐</a></li>
                <li><a href="/index?aType=4">其他</a></li>
            </ul>
            <#if (user)??>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/user/"><i class="icon-user"></i> 个人中心</a></li>
                    <li><a href="/user/article/public"><i class="icon-edit"></i> 发布文章</a></li>
                    <li><a href="/user/logout"><i class="icon icon-signout"></i> 退出登录</a></li>
                </ul>
            <#else>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/user/register"><i class="icon icon-user"></i> 注册</a></li>
                    <li><a href="/user/login"><i class="icon icon-signin"></i> 登录</a></li>
                </ul>
            </#if>

        </div><!-- END .navbar-collapse -->
    </div>
</nav>
<#--主体部分 -->
<div class="container">

