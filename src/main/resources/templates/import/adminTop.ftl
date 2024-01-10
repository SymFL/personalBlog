<#-- 管理员端通用头文件 -->
<#include "./top.ftl">

<div class="container">
    <nav class="navbar navbar-default" role="navigation">
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
                <a class="navbar-brand" href="/">首页</a>
            </div>
            <!-- 导航项目 -->
            <div class="collapse navbar-collapse navbar-collapse-example">
                <!-- 一般导航项目 -->
                <ul class="nav navbar-nav">
                    <li><a href="/csk2024/"><i class="icon icon-tasks"></i> 基础数据</a></li>
                    <!-- 导航中的下拉菜单 -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-user"></i> 用户管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/csk2024/user/list">用户列表</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-columns"></i> 文章管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/csk2024/article/type/list">文章类型</a></li>
                            <li><a href="/csk2024/article/tag/list">文章标签</a></li>
                            <li><a href="/csk2024/article/list">文章列表</a></li>
                        </ul>
                    </li>
                    <li><a href="/csk2024/link/list"><i class="icon icon-link"></i> 友情链接</a></li>
                    <li><a href="/csk2024/ad"><i class="icon icon-dollar"></i> 广告管理</a></li>
                </ul>
            </div><!-- END .navbar-collapse -->
        </div>
    </nav>