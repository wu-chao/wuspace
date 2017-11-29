$().ready(function () {

    //ajax post请求加入_token
    $.ajaxSetup({headers: {'X-XSRF-TOKEN': $('meta[name="_csrf"]').attr('content')}});


    //列表页搜索框搜索提示
    window.getSearchList = function getSearchList(listUrl, showUrl, searchId) {
        $("#" + searchId).bsSuggest({
            url: listUrl,
            getDataMethod: 'firstByUrl',
            emptyTip: '未检索到匹配的数据',
            idField: -1,
            keyField: 0,
            autoSelect: false,
            delayUntilKeyup: true,
            processData: function (json) {
                var i, data = {value: []};
                if (!json || json.length === 0) {
                    return false;
                }
                for (i = 0; i < json.length; i++) {
                    data.value.push({
                        name: json[i].name
                    });
                }
                data.defaults = '';
                return data;
            },
            autoMinWidth: false

        }).on('onSetSelectValue', function (e, keyword, data) {
            location.href = showUrl + data.name;
        });
    };

    // // 新增验证
    // jQuery.validator.addMethod(
    //     'phone',
    //     function(value, element){
    //         var pattern = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '请填写正确的手机号码'
    // );
    //
    // jQuery.validator.addMethod(
    //     'int',
    //     function(value, element){
    //         var pattern = /^[1-9]\d*$/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '请填写正整数'
    // );
    //
    // jQuery.validator.addMethod(
    //     'chineseText',
    //     function(value, element){
    //         var pattern = /[\u4e00-\u9fa5]/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '请填写中文'
    // );
    //
    // jQuery.validator.addMethod(
    //     'chineseName',
    //     function(value, element){
    //         var pattern = /[0-9\u4e00-\u9fa5]+$/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '请填写中文、数字'
    // );
    //
    // jQuery.validator.addMethod(
    //     'pointTwo',
    //     function(value, element){
    //         var pattern = /^\d+(\.\d{2})?$/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '仅限数字，小数点后保留两位小数'
    // );
    //
    // jQuery.validator.addMethod(
    //     'pointFour',
    //     function(value, element){
    //         var pattern = /^\d+(\.\d{4})?$/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '仅限数字，小数点后保留四位小数'
    // );
    //
    // jQuery.validator.addMethod(
    //     'memberName',
    //     function(value, element){
    //         var pattern = /^([\u4E00-\uFA29]*[a-z]*[A-Z]*)+$/;
    //         return this.optional(element) || pattern.test(value);
    //     },
    //     '仅限中文，英文'
    // );

    // $('#productNewEdit').validate({
    //     rules: {
    //         name: {
    //             required: true,
    //             maxlength: 20
    //         },
    //         type: "required",
    //         returnRate: "required",
    //         netValue: "required",
    //         maxWithdrawal: "required",
    //         currentReturnRate: "required",
    //         cumulativeRate: "required",
    //         total: "required",
    //         term: "required",
    //         startingAmount: {
    //             required: true,
    //             maxlength: 12
    //         },
    //         advisor: "required",
    //         manager: "required",
    //         contact: {
    //             required: true,
    //             maxlength: 10
    //         },
    //         phone: {
    //             required: true,
    //             minlength: 11
    //         },
    //         baseInfo: "required",
    //         analysis: "required",
    //         editorCounselor: "required",
    //         editorManager: "required"
    //     },
    //     messages: {
    //         name: {
    //             required: "请填写中文、数字",
    //             maxlength: "最多只能填写20个字符"
    //         },
    //         type: "请选择产品类型",
    //         returnRate: "仅限数字，小数点后保留两位小数",
    //         netValue: "仅限数字，小数点后保留四位小数",
    //         maxWithdrawal: "仅限数字，小数点后保留两位小数",
    //         currentReturnRate: "仅限数字，小数点后保留两位小数",
    //         cumulativeRate: "仅限数字，小数点后保留两位小数",
    //         total: "请填写产品规模",
    //         term: "请填写正整数",
    //         startingAmount: {
    //             required: "请填写正整数",
    //             maxlength: "最多只能填写12个字符"
    //         },
    //         advisor: "请填写投资顾问",
    //         manager: "请填写投资经理",
    //         contact: {
    //             required: "请填写中文",
    //             maxlength: "最多只能填写10个字符"
    //         },
    //         phone: {
    //             required: "请填写正确的手机号码",
    //             minlength: "最少填写11个字符"
    //         },
    //         baseInfo: "请填写产品基本信息",
    //         analysis: "请填写产品分析",
    //         editorCounselor: "请填写投资顾问",
    //         editorManager: "请填写投资经理"
    //     },
    //
    //     errorPlacement: function(error, element) {
    //         error.appendTo(element.parents('.form-group'));
    //     }
    // });

    // ajax分页
    window.ajaxPagination = function ajaxPagination(url, hasParams, successFunction) {
        var totalElements = parseInt($('#pagination').data("totalelements"));
        var size = 10;
        var totalPages = Math.ceil(totalElements / size);
        if ($.isNumeric(totalPages)) {
            var options = {
                bootstrapMajorVersion: 3,
                alignment: 'center',
                currentPage: 1,
                totalPages: totalPages,
                pageUrl: function (type, page, current) {
                    if (hasParams) {
                        return url + "&page=" + (page - 1)
                    }
                    return url + "?page=" + (page - 1);
                },
                onPageClicked: function (event, originalEvent, type, page) {
                    originalEvent.preventDefault();
                    originalEvent.stopPropagation();
                    $.ajax({
                        url: originalEvent.target.href,
                        type: "GET",
                        dataType: "json",
                        success: function (data) {
                            successFunction(data);
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
                }
            };
            $(document).ready(function () {
                $("#pagination").bootstrapPaginator(options);
                $("#pagination li:first a").trigger("click");
            });
        }
    };

    // CKEditor
    resetCKeditor('content');

    //初始化 CKEditor 富文本编辑器的图片上传功能
    function resetCKeditor(element) {
        CKEDITOR.replace(element, {
            extraPlugins: 'uploadimage,image2',
            height: 300,

            image_previewText: '',
            filebrowserUploadUrl: '/ckeditor/upload/image',
            filebrowserImageUploadUrl: '/ckeditor/upload/image'

        });
    }

});
