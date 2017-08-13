+function ($) {
    'use strict';

    resetCKeditor('content');

    //初始化 CKEditor 富文本编辑器的图片上传功能
    function resetCKeditor(element) {
        CKEDITOR.replace(element, {
            extraPlugins: 'uploadimage,image2',
            height: 300,

            image_previewText: ' ',
            filebrowserUploadUrl: '/ckeditor/upload/image',
            filebrowserImageUploadUrl: '/ckeditor/upload/image',

            // The following options are not necessary and are used here for presentation purposes only.
            // They configure the Styles drop-down list and widgets to use classes.

            stylesSet: [
                {name: 'Narrow image', type: 'widget', widget: 'image', attributes: {'class': 'image-narrow'}},
                {name: 'Wide image', type: 'widget', widget: 'image', attributes: {'class': 'image-wide'}}
            ],

            // Configure the Enhanced Image plugin to use classes instead of styles and to disable the
            // resizer (because image size is controlled by widget styles or the image takes maximum
            // 100% of the editor width).
            image2_alignClasses: ['image-align-left', 'image-align-center', 'image-align-right'],
            image2_disableResizer: true

        });
    }

}(jQuery);
