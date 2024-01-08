<#-- 管理员端基础数据页面 -->
<#include "../import/adminTop.ftl">

<div class="panel">
    <div class="panel-body">
        <h3><i class="icon icon-desktop"></i> 系统名称：${osName}</h3>
        <h3><i class="icon icon-server"></i> 服务器地址：${hostAddress}</h3>
        <h3><i class="icon icon-user"></i> 用户数量：${userCount}</h3>
        <h3><i class="icon icon-file-text"></i> 文章数量：${articleCount}</h3>
        <h3><i class="icon icon-tags"></i> 文章标签数量：${articleTagCount}</h3>
    </div>
</div>

<#include "../import/adminBottom.ftl">