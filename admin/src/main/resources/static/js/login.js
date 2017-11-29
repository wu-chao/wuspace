+function ($) {
    'use strict';

    //刷新登录验证码图片
    function changeImg() {
        var imgSrc = $("#adminVerificationCodeImg");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", changeUrl(src));
    }

    function changeUrl(url) {
        var timestamp = (new Date()).valueOf();
        var index = url.indexOf("?");
        if (index > 0) {
            url = url.substring(0, index);
        }
        url = url + "?timestamp=" + timestamp;
        return url;
    }

    $('#adminVerificationCodeImg').on('click', function () {
        changeImg();
    });

    $("#adminLoginBtn").unbind('click').click(function () {
        var IsBy = $.idcode.validateCode();
        if (IsBy) {
            $("#adminLoginForm").submit();
        } else {
            toastr.error("验证码错误！")
        }
    });

}(jQuery);
