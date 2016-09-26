
var editor = editormd("editor_container", {
	height : '500px',
	path : 'http://localhost:8080/blog/resources/js/editormd/lib/',
	theme : 'dark',
    previewTheme : 'dark',
    editorTheme : 'pastel-on-dark',
    markdown : '',		// 数据库初始化文本
    codeFold : true,
    //syncScrolling : false,
    saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
    searchReplace : true,
    watch : true,                // 开启实时预览
    htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
    toolbar  : true,             //开启工具栏
    previewCodeHighlight : true, // 开启预览 HTML 的代码块高亮，默认开启
    emoji : true,
    taskList : true,
    tocm : true,         // Using [TOCM]
    tex : true,                   // 开启科学公式TeX语言支持，默认关闭
    flowChart : true,             // 开启流程图支持，默认关闭
    sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
    //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
    //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
    //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
    //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
    //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
    /*图片上传*/
	imageUpload : true,
	imageFormats : ['jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp'],
	imageUploadURL : '/blog/upload/uploadImage',
	uploadCallbackURL : 'http:localhost:8080/blog/upload/images/'
});


function editor_publish(object){
	var content = editor.getHTML();
	$("#content").val(content);
	return false;
}

$("#html").on("click",function(){
	alert(editor.getHTML());
});

$("#md").on("click",function(){
	alert(editor.getMarkdown());
});