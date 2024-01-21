<#-- 文章列表页面 -->
<#include "../import/adminTop.ftl">

<#--  查询面板  -->
<div class="panel">
    <div class="panel-body">
        <form class="form-horizontal" action="/csk2024/article/list" method="get">
            <div class="form-group">
                <label for="articleTitle" class="col-sm-1">文章标题:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="articleTitle" id="articleTitle"
                           placeholder="文章标题"
                           value="<#if (articleTitle?? && articleTitle?length>0)>${articleTitle!}<#else></#if>">
                </div>
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">查询</button>
                </div>
                <div class="col-sm-1">
                    <a href="/csk2024/article/list" class="btn btn-default">查询全部</a>
                </div>
            </div>
        </form>
    </div>
</div>

<h3>文章数：${articlePage.total}</h3>

<#--  列表面板  -->
<#if articlePage?? && articlePage.list?size gt 0>
    <div class="panel">
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>文章标题</th>
                    <th>发布者</th>
                    <th>文章类型</th>
                    <th>发布时间</th>
                    <th>点赞量</th>
                    <th>浏览量</th>
                    <th>收藏量</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list articlePage.list as article>
                    <tr>
                        <td>${(article.articleTitle)!}</td>
                        <td>${(article.userName)!}</td>
                        <td>${(article.articleTypeName)!}</td>
                        <td>${(article.articleAddTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${(article.articleGoodNumber)!}</td>
                        <td>${(article.articleLookNumber)!}</td>
                        <td>${(article.articleCollectionNumber)!}</td>
                        <td>
                            <button type="button"
                                    onclick="articleDelete('${article.articleId}','${articlePage.pageNumber}','${articlePage.list?size}')"
                                    class="btn btn-default">
                                <i class="icon icon-remove-sign"></i>删除
                            </button>
                            <button type="button"
                                    class="btn btn-default">
                                <i class="icon icon-eye-open"></i>查看
                            </button>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="panel-footer">
            <ul class="pager" id="myPager" data-ride="pager"
                data-page=${articlePage.pageNumber} data-rec-per-page=${articlePage.pageSize}
                data-rec-total=${articlePage.total}
                data-elements="prev_icon,nav,next_icon,page_of_total_text,total_text"
                data-link-creator="/csk2024/article/list?pageNumber={page}<#if (articleTitle?? && articleTitle?length>0)>&articleTitle=${(articleTitle)!}</#if>"></ul>
        </div>
    </div>
<#else >
    <div class="panel">
        <div class="panel-body">
            <h4><i class="icon icon-info-sign"></i>暂无数据</h4>
        </div>
    </div>
</#if>

<script>
    <#--  删除文章  -->

    function articleDelete(articleId, pageNumber, pageSize) {
        var number = parseInt(pageNumber);
        var size = parseInt(pageSize);
        if (confirm("确认要删除该文章吗？")) {
            if (!checkNotNull(articleId)) {
                zuiMsg("出现异常，请刷新页面");
                return;
            }

            $.post("/csk2024/article/delete",
                {
                    articleId: articleId
                },
                function (date) {
                    if (date.code === 200) {
                        alert(date.message);
                        if (number === 1 && size === 1) {
                            window.location.href = "/csk2024/article/list";
                            return;
                        } else if (number > 1 && size === 1) {
                            var url = new URL(window.location.href);
                            var params = url.searchParams;
                            params.set('pageNumber', (number - 1).toString());
                            url.search = params.toString();
                            window.location.href = url.toString();
                            return;
                        }
                        location.reload();
                        return;
                    }
                    zuiMsg(date.message);
                }
            );
        }
    }
</script>

<#include "../import/adminBottom.ftl">