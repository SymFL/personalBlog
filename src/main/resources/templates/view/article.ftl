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
        <span style="margin: 3px;cursor: pointer" class="label label-warning pull-right"><i class="icon icon-star"
                                                                                            onclick="collection('${article.articleId}')"></i> ${(article.articleCollectionNumber)!}</span>
        <span style="margin: 3px;cursor: pointer" class="label label-primary pull-right"
              style="padding-left: 20px;padding-right: 20px" onclick="good('${article.articleId}')"><i
                    class="icon icon-thumbs-up"></i> ${(article.articleGoodNumber)!}</span>
        <span style="margin: 3px;" class="label label-info pull-right"><i
                    class="icon icon-eye-open"></i> ${(article.articleLookNumber)!}</span>
        <#if comments?? && comments?size gt 0>
            <div class="comments">
                <header>
                    <h3>所有评论</h3>
                </header>
                <section class="comments-list">
                    <div class="comment">
                        <#list comments as comment>
                            <div class="content">
                                <div class="pull-right text-muted">${(comment.commentTime)?string("yyyy-MM-dd")}</div>
                                <div><a disabled="disabled"
                                        style="color: #c74743"><strong>${(comment.userName)!}</strong></a></div>
                                <div class="text">${(comment.commentContext)!}</div>
                                <div class="actions">
                                    <a onclick="deleteComment('${(comment.commentId)!}','${(comment.userId)!}')">删除</a>
                                </div>
                            </div>
                            <div style="border-top: 1px dashed #d5d2d2;"></div>
                        </#list>
                    </div>
                </section>
                <footer>
                    <div class="reply-form" id="commentForm">
                        <form class="form">
                            <div class="form-group">
                                <textarea class="form-control new-comment-text" rows="2"
                                          <#if user??><#else>disabled="disabled"</#if> id="commentContext"
                                          <#if user??>placeholder="撰写评论..."<#else>placeholder="请先登录再评论哦亲~"</#if>></textarea>
                            </div>
                            <div class="row">
                                <div class="col-sm-1 pull-right">
                                    <button type="button" <#if user??><#else>disabled="disabled"</#if>
                                            onclick="publishComment('${(article.articleId)!}')"
                                            class="btn btn-block btn-primary"><i class="icon-ok"> 发布</i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </footer>
            </div>
        </#if>
    </footer>
</article>
<script>
<#--  发布评论  -->
    function publishComment(articleId) {
        let context = $('#commentContext').val();
        if(!checkNotNull(context)){
            zuiMsg("消息提示：请写上评论内容哦亲~");
            return;
        }
        $.post("/article/comment/publish",
            {
                articleId: articleId,
                context: context
            },
            function (data){
                if(data.code === 200){
                    alert(data.message);
                    location.reload();
                    return;
                }
                zuiMsg(data.message);
            }
        );
    }

    <#--  删除评论  -->

    function deleteComment(commentId, userId) {
        if (confirm("确认要删除该评论吗？")) {
            if (!checkNotNull(commentId) || !checkNotNull((userId))) {
                zuiMsg("提示消息：系统错误，请刷新页面")
                return;
            }
            $.post("/article/comment/delete",
                {
                    commentId: commentId,
                    userId: userId
                },
                function (data) {
                    if (data.code === 200) {
                        alert(data.message);
                        location.reload();
                        return;
                    }
                    zuiMsg(data.message);
                }
            );
        }
    }

    <#--  收藏  -->

    function collection(articleId) {
        $.get("/article/collection",
            {
                articleId: articleId
            },
            function (data) {
                if (data.code === 200) {
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
            function (data) {
                if (data.code === 200) {
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