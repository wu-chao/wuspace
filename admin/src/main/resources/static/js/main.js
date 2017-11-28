+function ($) {
    'use strict';

    //ajax post请求加入_token
    $.ajaxSetup({headers: {'X-XSRF-TOKEN': $('meta[name="_csrf"]').attr('content')}});

}(jQuery);