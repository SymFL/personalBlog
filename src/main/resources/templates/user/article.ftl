<#include "../import/viewTop.ftl">
<#include "../import/userBar.ftl">
<div class="panel col-sm-10" style="padding-left: 0;padding-right: 0;">
    <div class="panel-heading">
        <i class="icon icon-bookmark"></i> 我的文章
    </div>
    <div class="panel-body">
        <form action="/user/article" method="get">
            <div class="input-group">
                <div class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
                     id="searchboxExample">
                    <input id="articleTitle" name="articleTitle" type="search" class="form-control search-input"
                           placeholder="搜索">
                    <label for="articleTitle" class="input-control-icon-left search-icon"><i
                                class="icon icon-search"></i></label>
                </div>
                <span class="input-group-btn"><button class="btn btn-primary" type="submit">搜索</button></span>
            </div>
        </form>
        <#if articlePage.list?size gt 0 >
            <div class="cards">
                <#list articlePage.list as article>
                    <div class="col-md-4 col-sm-6 col-lg-4">
                        <a class="card" href="###">
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
                                <div class="pull-right"><i class="icon icon-star"></i> ${article.articleCollectionNumber}</div>
                                <div class="pull-right" style="padding-left: 20px;padding-right: 20px"><i class="icon icon-thumbs-up"></i> ${article.articleGoodNumber}</div>
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
                data-link-creator="/user/article?pageNumber={page}<#if (articleTitle?? && articleTitle?length>0)>&articleTitle=${(articleTitle)!}</#if>"></ul>
        </div>
    </div>
</div>
<#include "../import/viewBottom.ftl">