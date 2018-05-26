/*发公告*/
function publishNotice(obj){
	var userId = $("#user_id").data("id");
	var title = $("#editor_title").val();
	var content = Editor.$txt.text();
	var contentHtml = Editor.$txt.html();
	if(isEmpty(title)){
		alert("请输入标题");
		$("#title").focus();
		return;
	}
	if(isEmpty(content)){
		alert("内容为空或者没有文字说明");
		Editor.$txt.focus();
		return;
	}
	content = contentHtml;
	$.ajax({
		type:"post",
		url:"/wuspace/notices/publish",
		data:{
			"adminId":userId,
			"title":title,
			"content":content
		},
		success:function(data){
			if(data.msg.trim() == "success"){
				var blogText = $(content).text();
				blogText = blogText.substr(0, 180);

				$("#blogs").prepend("<li class='blog'>"+
					"<div class='blog_header'>"+
					"	<a href='/wuspace/notices/"+data.id+"'>"+title+"</a>"+
					"</div>"+
					"<div class='blog_body'>"+blogText+"</div>"+
					"<div class='blog_footer'>"+
					"	<i style='background:url(/wuspace/images/blog_time.png) no-repeat'></i>"+
					"	<span class='blog_time'>"+getLocalTime(data.createTime)+"</span>&nbsp;&nbsp;"+
					"	<a data-id='"+data.id+"' onclick='deleteNotice(this)' th:text='删除'>删除</a>"+
					"</div>"+
				"</li>");
				
				var imgs = $(content).find("img");
				var len = imgs.length;
				
				if(len >= 1){
					var count = 0;
					var img_div = document.createElement("div");
					img_div.className = "map_div";
					for(var i=0; i<len && count<3; i++, count++){
						var imgDom = imgs[i];
						var imgSrc = imgDom.src;
						var realWidth = imgDom.width;
						var realHeight = imgDom.height;
						if(realWidth >= 150 || realHeight >= 105){
							var img = document.createElement("img");
							img.src = imgSrc;
							img.height = 105;
							img.width = 150;
							img.className = "map";
							img_div.appendChild(img);
						}
					}
					var $blog = $("#blogs .blog:first");
					$blog.find(".blog_header").html(title.substr(0,40));
					$blog.find(".blog_body").append(img_div);
				}
				
				$("#editor_title").val("");
				/*清空内容，不能传入空字符串，而必须传入如下参数*/
				Editor.$txt.html("<p><br></p>");
			}else{
				Editor.$txt.html("<p><br></p>");
				$("#editor_title").val("").focus();
			}
		}
	});	
}

function updateNotice(obj){
	
}

/*删除公告*/
function deleteNotice(obj){
	if(window.confirm("确认删除？")){
		var $this = $(obj);
		var noticeId = $this.data("id");
		$.ajax({
			type:"get",
			url:"/wuspace/notices/"+noticeId+"/delete",
			success:function(data){
				if(data.trim() == "success"){
					$this.parents(".blog").slideUp();
				}else{
					alert("删除失败");
				}
			}
		});
	}
}

function initNotices(){
	$("#module_notice").find(".module_nr").each(function(){
		var $content = $(this).find(".module_nr_content");
		var content = $content.text();
		content = content.length > 60 ? content.substr(0,60) : content;
		$content.text(content);
	});
}