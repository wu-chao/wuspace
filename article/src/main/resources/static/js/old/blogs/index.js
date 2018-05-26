+function ($) {
    'use strict';

    $(function () {
        ajaxPagination('/blog/blogs.json', false, ajaxSuccessPagination);
    });

    function ajaxSuccessPagination() {
        console.log("pagination success");
    }
}(jQuery);