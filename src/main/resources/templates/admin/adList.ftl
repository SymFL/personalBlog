<#--广告管理页面-->
<#include "../import/adminTop.ftl">
<link href="//cdn.bootcdn.net/ajax/libs/smalot-bootstrap-datetimepicker/2.4.4/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet">
<script src="//cdn.bootcdn.net/ajax/libs/smalot-bootstrap-datetimepicker/2.4.4/js/bootstrap-datetimepicker.min.js"></script>
<script src="//cdn.bootcdn.net/ajax/libs/smalot-bootstrap-datetimepicker/2.4.4/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<#--广告标签-->
<div class="panel col-sm-3">
    <div class="panel-body">
        <div class="panel">
            <div class="panel-heading">
                添加/修改广告类型
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input type="hidden" id="adTypeIdUpdate">
                        <label for="adTypeTitleUpdate" class="col-sm-5">类型名称</label>
                        <div class="col-md-7 col-sm-7">
                            <input type="text" class="form-control" id="adTypeTitleUpdate" placeholder="类型名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adTypeTagUpdate" class="col-sm-5">类型标签</label>
                        <div class="col-md-7 col-sm-7">
                            <input type="text" class="form-control" id="adTypeTagUpdate" placeholder="类型标签">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adTypeSortUpdate" class="col-sm-5">类型排序</label>
                        <div class="col-md-7 col-sm-7">
                            <input type="number" class="form-control" id="adTypeSortUpdate" placeholder="类型排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button"
                                    onclick="adTypeUpdateAction()"
                                    class="btn btn-default">提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel">
            <div class="panel-heading">
                广告类型列表
                <a href="/csk2024/ad/list">
                    <i class="icon icon-eye-open pull-right" data-toggle="tooltip" data-placement="bottom"
                       title="查看所有类型的广告"></i>
                </a>
            </div>
            <#if adTypes?? && adTypes?size gt 0>
                <div class="panel-body">
                    <#list adTypes as adType>
                        <a href="/csk2024/ad/list?adTypeId=${adType.adTypeId}" class="img-thumbnail"
                           style="margin-bottom: 10px;">${adType.adTypeTitle}</a>
                        <i class="icon icon-spin icon-cog"
                           data-toggle="tooltip" data-placement="bottom" title="修改"
                           onclick="adTypeUpdate('${(adType.adTypeId)!}','${(adType.adTypeTitle)!}','${(adType.adTypeTag)!}','${(adType.adTypeSort)!}')"
                        ></i>
                        <i class="icon icon-times"
                           data-toggle="tooltip" data-placement="bottom" title="删除"
                           onclick="adTypeDelete('${(adType.adTypeId)}')"></i>
                        <br>
                    </#list>
                </div>
            </#if>

        </div>
    </div>
</div>

<div class="panel col-sm-9">
    <div class="panel-body" style="padding: 5px">
        <div class="panel">
            <div class="panel-heading">
                添加/修改广告
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <input type="hidden" id="adIdUpdate">
                    <div class="form-group">
                        <label for="adTypeId" class="col-sm-2">广告类型</label>
                        <div class="col-md-6 col-sm-10">
                            <select class="form-control" id="adTypeId">
                                <#if adTypes?? && adTypes?size gt 0>
                                    <#list adTypes as adType>
                                        <option value="${(adType.adTypeId)!}">${(adType.adTypeTitle)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adTitleUpdate" class="col-sm-2">广告标题</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="adTitleUpdate" placeholder="广告标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adSortUpdate" class="col-sm-2">广告排序</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="adSortUpdate" placeholder="广告排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adImgUrlUpdate" class="col-sm-2">图片地址</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="adImgUrlUpdate" placeholder="图片地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adLinkUrlUpdate" class="col-sm-2">跳转链接</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control" id="adLinkUrlUpdate" placeholder="跳转链接">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adBeginTimeUpdate" class="col-sm-2">开始时间</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control form-datetime" id="adBeginTimeUpdate"
                                   placeholder="开始时间" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adEndTimeUpdate" class="col-sm-2">结束时间</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control form-datetime" id="adEndTimeUpdate"
                                   placeholder="结束时间" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button"
                                    onclick="adAddOrUpdateAction()"
                                    class="btn btn-default">提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel">
            <div class="panel-heading">
                广告列表
                <i class="icon icon-plus pull-right" onclick="adAdd()" data-toggle="tooltip" data-placement="bottom"
                   title="添加"></i>
            </div>
            <#if ads?? && ads?size gt 0>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>排序</th>
                            <th>广告标题</th>
                            <th>广告类型</th>
                            <th>广告图片</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list ads as ad>
                            <tr>
                                <td>${(ad.adSort)!}</td>
                                <td>${(ad.adTitle)!}</td>
                                <td>${(ad.adTypeTitle)!}</td>
                                <td><img class="thumbnail img-thumbnail" src="${ad.adImgUrl}" alt="广告图片"/></td>
                                <td>${(ad.adBeginTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td>${(ad.adEndTime)?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td>
                                    <a href="${(ad.adLinkUrl)!}" target="_blank"
                                       data-toggle="tooltip" data-placement="bottom" title="查看"
                                       class="btn btn-default">
                                        <i class="icon-eye-open"></i></a>
                                    <button type="button"
                                            data-toggle="tooltip" data-placement="bottom" title="修改"
                                            onclick="adUpdate('${(ad.adId)!}',
                                                    '${(ad.adTypeId)!}',
                                                    '${(ad.adLinkUrl)!}',
                                                    '${(ad.adImgUrl)!}',
                                                    '${(ad.adTitle)!}',
                                                    '${(ad.adSort)!}',
                                                    '${(ad.adBeginTime)?string("yyyy-MM-dd HH:mm:ss")}',
                                                    '${(ad.adEndTime)?string("yyyy-MM-dd HH:mm:ss")}')"
                                            class="btn btn-default">
                                        <i class="icon icon-spin icon-cog"></i>
                                    </button>
                                    <button type="button"
                                            data-toggle="tooltip" data-placement="bottom" title="删除"
                                            onclick="adDelete('${(ad.adId)!}')"
                                            class="btn btn-default">
                                        <i class="icon icon-remove-sign"></i>
                                    </button>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </#if>

        </div>
    </div>
</div>
<script>
    <#--  添加/修改广告提交  -->

    function adAddOrUpdateAction() {
        let adId = $('#adIdUpdate').val();
        let adLinkUrl = $('#adLinkUrlUpdate').val();
        let adImgUrl = $('#adImgUrlUpdate').val();
        let adTitle = $('#adTitleUpdate').val();
        let adSort = $('#adSortUpdate').val();
        let adTypeId = $('#adTypeId').val();
        let adBeginTime = $('#adBeginTimeUpdate').val();
        let adEndTime = $('#adEndTimeUpdate').val();

        if (!checkNotNull(adLinkUrl) &&
            !checkNotNull(adImgUrl) &&
            !checkNotNull(adTitle) &&
            !checkNotNull(adSort) &&
            !checkNotNull(adTypeId) &&
            !checkNotNull(adBeginTime) &&
            !checkNotNull(adEndTime)){
            zuiMsg("提示消息：请完整填写表格！")
        }

            $.post("/csk2024/ad/addOrUpdate",
                {
                    adId: adId,
                    adLinkUrl: adLinkUrl,
                    adImgUrl: adImgUrl,
                    adTitle: adTitle,
                    adSort: adSort,
                    adTypeId: adTypeId,
                    adBeginTime: adBeginTime,
                    adEndTime: adEndTime
                },
                function (date) {
                    if (date.code === 200) {
                        alert(date.message);
                        location.reload();
                        return;
                    }
                    zuiMsg(date.message);
                }
            );
    }

    <#--  添加广广告  -->

    function adAdd() {
        $('#adIdUpdate').val("");
        $('#adLinkUrlUpdate').val("");
        $('#adImgUrlUpdate').val("");
        $('#adTitleUpdate').val("");
        $('#adSortUpdate').val("");
        $('#adTypeId').val("");
        $('#adBeginTimeUpdate').val("");
        $('#adEndTimeUpdate').val("");
    }

    <#--  修改广告  -->

    function adUpdate(adId, adTypeId, adLinkUrl, adImgUrl, adTitle, adSort, adBeginTime, adEndTime) {
        $('#adIdUpdate').val(adId);
        $('#adLinkUrlUpdate').val(adLinkUrl);
        $('#adImgUrlUpdate').val(adImgUrl);
        $('#adTitleUpdate').val(adTitle);
        $('#adSortUpdate').val(adSort);
        $('#adTypeId option[value="' + adTypeId + '"]').prop("selected", "selected");
        $('#adBeginTimeUpdate').val(adBeginTime);
        $('#adEndTimeUpdate').val(adEndTime);
    }

    <#--  删除广告  -->

    function adDelete(adId) {
        console.log(adId)
        if (confirm("是否确定要删除该广告")) {
            if (!checkNotNull(adId)) {
                zuiMsg('提示消息：出现错误，请刷新页面');
                return;
            }
            $.post("/csk2024/ad/delete",
                {
                    adId: adId
                },
                function (data) {
                    if (data.code === 200) {
                        alert(data.message);
                        location.reload();
                        return;
                    }
                    zuiMsg("提示消息：" + data.message);
                });
        }
    }


    <#--时间控件-->
    $(".form-datetime").datetimepicker(
        {
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1,
            format: "yyyy-mm-dd hh:ii"
        });

    <#--  广告类型删除  -->

    function adTypeDelete(adTypeId) {
        if (confirm("是否确定要删除该类型")) {
            if (!checkNotNull(adTypeId)) {
                zuiMsg('提示消息：类型 id 不允许为空');
                return;
            }
            $.post("/csk2024/ad/type/delete",
                {
                    adTypeId: adTypeId
                },
                function (data) {
                    if (data.code === 200) {
                        alert(data.message);
                        location.reload();
                        return;
                    }
                    zuiMsg("提示消息：" + data.message);
                });
        }
    }

    <#--  修改/添加广告类型提交  -->

    function adTypeUpdateAction() {
        let adTypeId = $('#adTypeIdUpdate').val();
        let adTypeTitle = $('#adTypeTitleUpdate').val();
        let adTypeTag = $('#adTypeTagUpdate').val();
        let adTypeSort = $('#adTypeSortUpdate').val();

        if (!checkNotNull(adTypeTitle) || !checkNotNull(adTypeTag) || !checkNotNull(adTypeSort)) {
            zuiMsg("提示消息：数据缺失，请填写缺失的信息");
            return;
        }

        $.post("/csk2024/ad/type/addOrUpdate",
            {
                adTypeId: adTypeId,
                adTypeTitle: adTypeTitle,
                adTypeTag: adTypeTag,
                adTypeSort: adTypeSort
            },
            function (date) {
                if (date.code === 200) {
                    alert(date.message);
                    location.reload();
                    return;
                }
                zuiMsg(date.message);
            }
        )
    }

    <#--  修改广告类型  -->

    function adTypeUpdate(adTypeId, adTypeTitle, adTypeTag, adTypeSort) {
        $('#adTypeIdUpdate').val(adTypeId);
        $('#adTypeTitleUpdate').val(adTypeTitle);
        $('#adTypeTagUpdate').val(adTypeTag);
        $('#adTypeSortUpdate').val(adTypeSort);
    }

</script>

<#include "../import/adminBottom.ftl">