+function ($) {
    'use strict';

    $(function () {
        ajaxPagination('/blogs', false, ajaxSuccessPagination);
    });

    function ajaxSuccessPagination() {
        alert("pagination success");
    }
}(jQuery);