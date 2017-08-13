+function ($) {
    'use strict';

    $(function () {
        ajaxPagination('/blogs', false, ajaxSuccessPagination);
    });

    function ajaxSuccessPagination() {
        console.log("pagination success");
    }
}(jQuery);