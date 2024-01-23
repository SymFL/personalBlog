<#--首页-->
<#include "../import/viewTop.ftl">
<div class="container col-sm-9">
    <#if !(aType??) && !(articleTitle??) && ads?? && ads?size gt 0>
        <div id="myNiceCarousel" class="carousel slide" data-ride="carousel">
            <!-- 圆点指示器 -->
            <ol class="carousel-indicators">
                <#list 0..<(ads?size) as i>
                    <li data-target="#myNiceCarousel" data-slide-to=${i} <#if i == 0>class="active"</#if>></li>
                </#list>
            </ol>

            <!-- 轮播项目 -->
            <div class="carousel-inner">
                <#list ads as ad>
                    <#if ad_index == 0>
                        <div class="item active">
                            <a target="_blank" href="${(ad.adLinkUrl)!}">
                                <img alt=${(ad.adTitle)!} src="${(ad.adImgUrl)!}">
                            </a>
                        </div>
                    <#else >
                        <div class="item">
                            <a target="_blank" href="${(ad.adLinkUrl)!}">
                                <img alt=${(ad.adTitle)!} src="${(ad.adImgUrl)!}">
                            </a>
                        </div>
                    </#if>
                </#list>
            </div>
            <!-- 项目切换按钮 -->
            <a class="left carousel-control" href="#myNiceCarousel" data-slide="prev">
                <span class="icon icon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#myNiceCarousel" data-slide="next">
                <span class="icon icon-chevron-right"></span>
            </a>
        </div>
    </#if>
    <#if articlePage.list?size gt 0 >
        <div class="cards">
            <#list articlePage.list as article>
                <div class="col-md-4 col-sm-6 col-lg-3">
                    <a class="card" href="/article?articleID=${article.articleId}">
                        <#if article.articleTypeName == "技术分享">
                            <img src="/img/ganyu.jpg" alt="">
                        </#if>
                        <#if article.articleTypeName == "游戏推荐">
                            <img src="/img/keli.png" alt="">
                        </#if>
                        <#if article.articleTypeName == "软件推荐">
                            <img src="/img/xiaogong.png" alt="">
                        </#if>
                        <#if article.articleTypeName == "其他">
                            <img src="/img/qiqi.jpg" alt="">
                        </#if>
                        <div class="card-heading"><strong>${article.articleTitle}</strong></div>
                        <div class="card-actions">
                            <span class="label label-warning">${article.articleTypeName}</span>
                            <div class="pull-right"><i class="icon icon-eye-open"></i> ${article.articleLookNumber}</div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </#if>
    <div class="container">
        <ul class="pager" id="myPager" data-ride="pager"
            data-page=${articlePage.pageNumber} data-rec-per-page=${articlePage.pageSize}
            data-rec-total=${articlePage.total}
            data-elements="prev_icon,nav,next_icon,page_of_total_text,total_text"
            data-link-creator="/index?pageNumber={page}<#if (articleTitle?? && articleTitle?length>0)>&articleTitle=${(articleTitle)!}</#if><#if (aType?? && aType?length>0)>&aType=${(aType)!}</#if>"></ul>
    </div>
</div>

<div class="container col-sm-3">
    <div style="padding-bottom: 15px">
        <form action="/index" method="get">
            <div class="input-group">
                <div class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
                     id="searchboxExample">
                    <#if aType?? && aType?length gt 0>
                        <input id="articleType" name="aType" value=${aType} type="hidden">
                    </#if>
                    <input id="articleTitle" name="articleTitle" type="search" class="form-control search-input"
                           placeholder="搜索">
                    <label for="articleTitle" class="input-control-icon-left search-icon"><i
                                class="icon icon-search"></i></label>
                </div>
                <span class="input-group-btn"><button class="btn btn-primary" type="submit">搜索</button></span>
            </div>
        </form>
    </div>


    <div class="list-group">
        <a href="#" class="list-group-item active">友情链接</a>
        <#list links as link>
            <a href=${link.linkUrl} class="list-group-item">${link.linkTitle}</a>
        </#list>
    </div>

</div>

<#include "../import/viewBottom.ftl">