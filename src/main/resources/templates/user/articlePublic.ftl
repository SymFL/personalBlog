<#--文章发布页面-->
<#include "../import/viewTop.ftl">
<link href="https://unpkg.com/@wangeditor/editor@latest/dist/css/style.css" rel="stylesheet">
<script src="https://unpkg.com/@wangeditor/editor@latest/dist/index.js"></script>
<div class="panel">
    <div class="panel-body">
        <div class="form-horizontal">
            <div class="form-group">
                <label for="articleTitle" class="col-sm-2">标题</label>
                <div class="col-md-6 col-sm-10">
                    <#if articleUpdate??>
                        <input type="text" class="form-control" id="articleTitle" value="${articleUpdate.articleTitle}">
                    <#else>
                        <input type="text" class="form-control" id="articleTitle" placeholder="请输入文章标题">
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="articleType" class="col-sm-2">类型</label>
                <div class="col-sm-3">
                    <select class="form-control" id="articleType">
                        <option value="">--请选择--</option>
                        <#list articleTypes as articleType>
                            <#if articleUpdate?? && articleType.articleTypeId == articleUpdate.articleTypeId>
                                <option value="${articleType.articleTypeId}" selected="selected">${articleType.articleTypeName}</option>
                            <#else>
                                <option value="${articleType.articleTypeId}">${articleType.articleTypeName}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="articleTag" class="col-sm-2">标签</label>
                <div class="col-sm-10">
                    <#list articleTags as articleTag>
                        <#if activeArticleTags?? && activeArticleTags?size gt 0>
                            <#if activeArticleTags?seq_contains(articleTag.articleTagId)>
                                <span id="${articleTag.articleTagId}" onclick="selectTag('${(articleTag.articleTagId)!}')"
                                      class="label label-badge label-primary" style="user-select: none">${articleTag.articleTagName}</span>
                            <#else >
                                <span id="${articleTag.articleTagId}" onclick="selectTag('${(articleTag.articleTagId)!}')"
                                      class="label label-badge" style="user-select: none">${articleTag.articleTagName}</span>
                            </#if>
                        <#else >
                            <span id="${articleTag.articleTagId}" onclick="selectTag('${(articleTag.articleTagId)!}')"
                                  class="label label-badge" style="user-select: none">${articleTag.articleTagName}</span>
                        </#if>
                    </#list>
                </div>
            </div>
        </div>
    </div>
    <#--  富文本编辑器  -->
    <div class="panel-body">
        <div id="editor—wrapper">
            <div id="toolbar-container"><!-- 工具栏 --></div>
            <div id="editor-container"><!-- 编辑器 --></div>
        </div>
    </div>

    <div class="panel-body">
        <button class="btn btn-success pull-right" onclick="articlePublic('${(articleUpdate.articleId)!}')" type="button"><i
                    class="icon icon-check"></i> 提交
        </button>
    </div>

</div>

<script>
    let articleTagIds = [];
    <#--  选择的标签  -->
    $(function () {
        let selectedTags = $("span.label-primary");
        for (let i = 0; i < selectedTags.length; i++) {
            articleTagIds[i] = selectedTags[i].id;
        }
    });
    <#--  选择标签  -->
    function selectTag(articleTagId) {
        let index = $.inArray(articleTagId, articleTagIds);
        if (index !== -1) {
            articleTagIds.splice(index, 1);
            $('#' + articleTagId).removeClass("label-primary");
        } else {
            articleTagIds.push(articleTagId);
            $('#' + articleTagId).addClass("label-primary");
        }
    }
    <#--  提交文章  -->
    function articlePublic(articleId) {
        let articleTitle = $('#articleTitle').val();
        let articleTypeId = $('#articleType').val();
        let shtml = Base64.encode(editor.getHtml());
        if (!checkNotNull(articleTitle)) {
            zuiMsg("提示消息：请输入文章标题！");
            return;
        }
        if (articleTitle.length < 3 || articleTitle.length > 50) {
            zuiMsg("提示消息：文章标题的长度为 3 - 50！")
            return;
        }
        if (!checkNotNull(articleTypeId)) {
            zuiMsg("提示消息：请选择文章类型！");
            return;
        }
        if (shtml === "<p><br></p>") {
            zuiMsg("提示消息：请输入文章正文！");
            return;
        }
        $.post("/user/article/upload",
            {
                articleId: articleId,
                articleTitle: articleTitle,
                articleTypeId: articleTypeId,
                html: shtml,
                articleTagIds: articleTagIds.join(",")
            },
            function (date) {
                if (date.code === 200) {
                    alert(date.message);
                    window.location.href = "/user/article";
                    return;
                }
                zuiMsg(date.message);
            }
        )

    }


    const {createEditor, createToolbar} = window.wangEditor;

    const editorConfig = {
        placeholder: '请在此书写正文，推荐使用全屏模式进行书写',
        MENU_CONF: {}
    }

    editorConfig.MENU_CONF['uploadImage'] = {
        server: '/user/upload/img',
        fieldName: 'img',
        maxFileSize: 10 * 1024 * 1024,
    }

    let encodedHtml = Base64.decode('${(articleUpdate.articleContext)!}')
    const editor = createEditor({
        selector: '#editor-container',
        html: encodedHtml,
        config: editorConfig,
        mode: 'default', // or 'simple'
    })

    const toolbarConfig = {}
    toolbarConfig.excludeKeys = [
        'group-video'
    ]

    const toolbar = createToolbar({
        editor,
        selector: '#toolbar-container',
        config: toolbarConfig,
        mode: 'default', // or 'simple'
    })
</script>
<#include "../import/viewBottom.ftl">
