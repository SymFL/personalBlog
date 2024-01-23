<#include "../import/viewTop.ftl">
<script src="https://unpkg.com/prismjs@latest/prism.js"></script>
<script src="https://unpkg.com/prismjs@latest/components/prism-core.js"></script>
<script src="https://unpkg.com/prismjs@latest/plugins/autoloader/prism-autoloader.js"></script>
<article class="article">
    <!-- 文章头部 -->
    <header>
        <h1 style="text-align: center">${(article.articleTitle)!}</h1>


        <!-- 文章属性列表 -->
        <dl class="dl-inline">
            <dd>
                <#if articleTags?? && articleTags?size gt 0>
                    <#list articleTags as articleTag>
                        <span class="label label-primary" style="margin: 3px;">${(articleTag.articleTagName)!}</span>
                    </#list>
                </#if>
            </dd>
            <dd style="float: right">${(article.articleAddTime)?string("yyyy-MM-dd HH-mm-ss")}</dd>
            <dt style="float: right"><i class="icon icon-time"></i> 发布时间:</dt>
            <dd style="float: right">${article.userName}</dd>
            <dt style="float: right"><i class="icon icon-user"></i> 发布者:</dt>
        </dl>
    </header>
    <!-- 文章正文部分 -->
    <section class="content"></section>
    <!-- 文章底部 -->
    <footer>

            <span style="margin: 3px;cursor: pointer" class="label label-warning pull-right"><i class="icon icon-star" onclick="collection('${article.articleId}')"></i> ${(article.articleCollectionNumber)!}</span>
            <span style="margin: 3px;cursor: pointer" class="label label-primary pull-right" style="padding-left: 20px;padding-right: 20px" onclick="good('${article.articleId}')"><i class="icon icon-thumbs-up"></i> ${(article.articleGoodNumber)!}</span>
            <span style="margin: 3px;" class="label label-info pull-right"><i class="icon icon-eye-open"></i> ${(article.articleLookNumber)!}</span>

    </footer>
</article>
<script>
<#--  收藏  -->
    function collection(articleId) {
        $.get("/article/collection",
            {
                articleId: articleId
            },
            function (data){
                if(data.code === 200){
                    zuiMsg(data.message);
                    return;
                }
                zuiMsg(data.message);
            }
        )
    }

    <#--  点赞  -->
    function good(articleId) {
        $.get("/article/good",
            {
                articleId: articleId
            },
            function (data){
                if(data.code === 200){
                    zuiMsg(data.message);
                    return;
                }
                zuiMsg(data.message);
            }
        )
    }

    // 解码文章内容
    let decodedContent = Base64.decode('${article.articleContext}');
    // 找到content section
    let contentSection = document.querySelector('section.content');
    // 将解码后的文章内容插入到content section中
    contentSection.innerHTML = decodedContent;
    Prism.highlightAll();
</script>
<#include "../import/viewBottom.ftl">