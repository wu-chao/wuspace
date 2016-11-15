/*初始化内容格式*/
/*$("#blist").find(".bitem").each(function(){
	var $blog = $(this).find(".btxt");
	var blogText_trim = $blog.text().substr(0, 180);
	$blog.text(blogText_trim);

});*/

/*发帖*/
function publishBlog(){
	var $selectedTopicType = $("#editor_topics option:selected");
	var topicType = $selectedTopicType.val();
	var topicTypeText = $selectedTopicType.text();
	var title = $("#editor_title").val();
	var content = Editor.$txt.text();
	var contentHtml = Editor.$txt.html();
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	if(topicType == "-1"){
		alert("请选择话题类别");
		return;
	}
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
	
	var imgs = $(content).find("img");
	var len = imgs.length;
	var imgSrc = "";
	if(len >= 1){
		var count = 0;
		for(var i=0; i<len && count<1; i++){
			var imgDom = imgs[i];
			imgSrc = imgDom.src;
			var realWidth = imgDom.width;
			var realHeight = imgDom.height;
			if(realWidth >= 150 || realHeight >= 105){
				count++;
			}
		}
	}
	
	$.ajax({
		type:"post",
		url:"/blog/blogs/publish",
		data:{
			"topicType":topicType,
			"userId":user_id,
			"title":title,
			"content":content,
			"image":imgSrc
		},
		success:function(data){
			if(data.msg.trim() == "success"){
				if(title.length + topicTypeText.length > 36){
					title = title.substr(0, 36 - topicTypeText.length) + "...";
				}
				title = "<span style='color:#F2934A'>【"+ topicTypeText +"】</span>" + title;
				
				var blogText = $(content).text();
				blogText = blogText.substr(0, 180);
			

				$("#blist").prepend("<li class='blog'>"+
					"<div class='blog_header'>"+
					"	<a href='/blog/blogs/"+data.id+"'>"+title+"</a>"+
					"</div>"+
					"<div class='blog_body'>"+blogText+"</div>"+
					"<div class='blog_footer'>"+
					"	<i style='background:url(/blog/resources/images/blog_author.png) no-repeat'></i>"+
					"	<a style='color:#999' class='blog_author' target='_blank' href='/blog/users/"+$("#user_id").data("id")+"'>"+$("#user_id").next().html()+"</a>&nbsp;&nbsp;"+
					"	<i style='background:url(/blog/resources/images/blog_time.png) no-repeat'></i>"+
					"	<span class='blog_time'>"+getLocalTime(data.createTime)+"</span>&nbsp;&nbsp;"+
					"	<i style='background:url(/blog/resources/images/blog_view_times.png) no-repeat'></i>"+
					"	<span>0</span>&nbsp;&nbsp;"+
					"	<a data-id='"+data.id+"' onclick='deleteBlog(this)'>删除</a>"+
					"   <a href='/blog/blogs/"+ data.id +"/update'>编辑</a>"+
					"</div>"+
				"</li>");
				
				
				
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


/*删帖*/
function deleteBlog(obj) {
	if(window.confirm("确认删除？")){
		var $this = $(obj);
		var blogId = $this.data("id");
		$.ajax({
			type:"get",
			url:"/blog/blogs/"+blogId+"/delete",
			success:function(data){
				if(data.trim() == "success"){
					$this.parents(".bitem").slideUp(function(){
						$(this).remove();
					});
				}else{
					alert("删除失败");
				}
			}
		});
	}
}

function initNewestBlogs(){
	$("#module_newesBlogs").find(".blog_newest").each(function(){
		var $content = $(this).find(".blog_newest_content");
		var content_text = $content.text().trim();
		content_text = content_text.length > 60 ? content_text.substr(0,60) + "..." : content_text;
		$content.text(content_text);
		
		/*
		 * if(content_text.length > 60){
		 * 	content_text = content_text.substr(0,60)+"...";
		 * }
		 * $content.text(content_text);
		 * */
	});
}




/*点赞*/
$("#blog_operations_zan").click(function(){
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	$.ajax({
		type:"post",
		url:"/blog/blogs/"+ blog_id +"/zan",
		data:{
			"userId":user_id
		},
		success:function(data){
			var $this = $("#blog_operations_zan");
			var $span = $this.find("span");
			var zanNum = parseInt($span.text());
			zanNum++;
			$this.html("已赞<span>"+ zanNum +"</span>");
			$this.addClass("visited no_hover").unbind("click");
			return;
		}
	});
});

/*收藏*/
$("#blog_operations_collect").click(function(){
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	$.ajax({
		type:"post",
		url:"/blog/blogs/"+ blog_id +"/collect",
		data:{
			"userId":user_id
		},
		success:function(data){
			var $this = $("#blog_operations_collect");
			var $span = $this.find("span");
			var collectNum = parseInt($span.text());
			collectNum++;
			$this.html("已收藏<span>"+ collectNum +"</span>");
			$this.addClass("visited no_hover").unbind("click");
		}
	});
});

function cancelCollect(obj){
	var userId = $("#user_id").data("id");
	var $this = $(obj);
	//收藏的帖子id
	var blogId = $this.data("id");
	$.ajax({
		type:"get",
		url:"/blog/blogs/"+ blogId +"/cancelCollect",
		data:{
			"userId":userId
		},
		success:function(data){
			if(data.trim()=="success"){
				$this.parents(".blog").slideUp();
			}
		}
	});
}

/*赞评论*/
function zanComment(obj){
	var reply_id;
	var url_selector = $(obj).parents("li").attr("class");
	if(url_selector.substr(0,2) == "co"){
		url_selector = "comments";
		reply_id = $(obj).parents(".citem").find(".cr_header input").data("id");
	} else{
		url_selector = "replies";
		reply_id = $(obj).parents(".reply").find(".cr_header input").data("id");
	}

	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	$.ajax({
		type:"post",
		url:"/blog/"+ url_selector +"/"+ reply_id +"/zan",
		data:{
			"userId":user_id
		},
		success:function(data){
			if(data.trim() == "success"){
				var $this = $(obj);
				var $span = $this.find("span");
				var zanNum = parseInt($span.text());
				$span.text(++zanNum);
				$this.addClass("cr_operations_visited cr_operations_nohover").unbind("click");
				return;
			}else{
				return;
			}
		}
	});
}

/*踩评论*/
function caiComment(obj){
	var reply_id;
	var url_selector = $(obj).parents("li").attr("class");
	if(url_selector.substr(0,2) == "co"){
		url_selector = "comments";
		reply_id = $(obj).parents(".citem").find(".cr_header input").data("id");
	} else{
		url_selector = "replies";
		reply_id = $(obj).parents(".reply").find(".cr_header input").data("id");
	}

	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	$.ajax({
		type:"post",
		url:"/blog/"+ url_selector +"/"+ reply_id +"/cai",
		data:{
			"userId":user_id
		},
		success:function(data){
			if(data.trim() == "success"){
				var $this = $(obj);
				var $span = $this.find("span");
				var zanNum = parseInt($span.text());
				$span.text(++zanNum);
				$this.addClass("cr_operations_visited cr_operations_nohover").unbind("click");
				return;
			}else{
				return;
			}
		}
	});
}

/*发表评论*/
var timer = null;    		
function publishComment(obj){
	var editor = Editor.$txt;
	//获取编辑器春文本内容
	var commentText = editor.text();
	var commentHtml = editor.html();
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	if(isEmpty(commentText) && $(commentHtml).find("img").length <= 0){
		alert("请输入内容");
		editor.focus();
		return;
	}
	clearTimeout(timer);
	timer = setTimeout(function(){
		$.ajax({
			type:"post",
			url:"/blog/comments/publish",
			data:{
				"userId":user_id,
				"blogId":blog_id,
				"content":commentHtml
			},
			success:function(data){
				if(data.msg.trim() == "success"){
					var $comments = $("#clist");
					var c_len = $comments.find(".citem").length;
					if(c_len <= 0){
						$("#comment_container").prepend("<div id='csize'>共&nbsp;<em style='color:#D8582B'>0</em>&nbsp;条评论</div>");
					}
					//评论
					var chtml = 
						"	<li class='citem' data-id = '"+ data.id +"'>"+
						"		<img class='cavatar' src='/blog/resources/images/avatars/"+ data.avatar +"'/>"+
						"		<div class='cbody'>"+
						"			<div class='cuser'>"+
						"				<a class='cuser_name' data-id='"+ user_id +"' target='_blank' href='/blog/users/"+ user_id +"'>"+ user_name +"</a> &nbsp;"+
						"				<a onclick='deleteComment(this)'>删除</a>"+
						"			</div>"+
						"			<div class='ctxt'>"+ commentHtml +"</div>"+
						"			<div class='cinfo'>"+
						"				<span class='ctime'>评论于&nbsp;<span>"+ getLocalTime(data.createTime) +"</span></span>"+
						"				<div class='cactions'>"+
						"					<a class='cactions_zan' onclick='zanComment(this)'>"+
						"						<span></span>"+
						"					</a>"+
						"					<a class='cactions_cai' onclick='caiComment(this)'>"+
						"						<span></span>"+
						"					</a>"+
						"					<a class='cactions_hui' onclick='replyComment(this)'>"+
						"						<span>回复</span>"+
						"					</a>"+
						"				</div>"+
						"			</div>"+
						"		</div>"+
						"		<div class='recomment_container'>" +
						"			<ul class='reclist'></ul>" +
						"		</div>"+
						"	</li>";
					$comments.prepend(chtml);
					$("#csize em").text(++c_len);
					$("#blog_comment_times span").text(c_len);
					editor.html("<p><br></p>");
				}else{
					editor.html("<p><br></p>").focus();
				}
			}//success
		});//ajax
	}, 2000);//setTimeout
}

/*展开或者隐藏再评论*/
function showorhideRecomment(obj){
	var $this = $(obj);
	var $reclist = $this.parent().next();
	if($reclist.is(":hidden")){
		$reclist.show();
		$this.text("收起");
	}else{
		$reclist.hide();
		$this.text("展开");
	}
}

/*回复评论*/
function replyComment(obj){
	if(reply_btn_flag == 0){
		var $parent = $(obj).parent();
		showReplyCommentEditor($parent);
	} 
	else if(reply_btn_flag == 1){
		cancelReplyComment();
	}
}

/*回复再评论*/
function replyRecomment(obj){
	if(reply_btn_flag == 0){
		var $parent = $(obj).parent();
		showReplyRecommentEditor($parent);
	} 
	else if(reply_btn_flag == 1){
		cancelReplyComment();
	}
}

/*ajax发表评论和再评论*/
function ajax_pc($reclist, commentId, user_id, replyToId, replyTo, recHtml){
	$.ajax({
		type:"post",
		url:"/blog/replies/publish",
		data:{
			"commentId":commentId,
			"userId":user_id,
			"replyToId":replyToId,
			"recHtml":recHtml
		},
		success:function(data){
			if(data.msg.trim() == "success"){
				var cSize = $reclist.find(".recitem").length;
				if(cSize <= 0){
					$reclist.parent().prepend("<div class='csize'>共&nbsp;<em style='color:#D8582B'>0</em>&nbsp;条回复</div>");
				}
				$reclist.append(
					"<li class='recitem' data-id='"+ data.id +"'>"+
					"	<img class='cavatar' src='/blog/resources/images/avatars/"+ data.avatar +"'/>"+
					"	<div class='cbody'>"+
					"		<div class='cuser'>"+
					"			<a class='cuser_name' target='_blank' href='/blog/users/"+ user_id +"' data-id = '"+ user_id +"'>"+ user_name +"</a>&nbsp;"+
					"			<a style='color:#337ab7' target='_blank' href='/blog/users/"+ replyToId +"'>"+
					"				@<span style='color:#337ab7' th:utext='${reply.replyTo.nickname}'>"+ replyTo +"</span></a>&nbsp;"+
					"			<a onclick='deleteReComment(this)'>删除</a>"+
					"		</div>"+
					"		<div class='ctxt'>"+ recHtml +"</div>"+
					"		<div class='cinfo'>"+
					"			<span class='ctime'>回复于&nbsp;<span>"+ getLocalTime(data.createTime) +"</span></span>"+
					"			<div class='cactions'>"+
					"				<a th:if='${!reply.existsZanUser(session.loginUser)}' class='cactions_zan' onclick='zanComment(this)'>"+
					"					<span th:text='${reply.zanUsers.size()}'></span>"+
					"				</a>"+
					"				<a th:if='${!reply.existsCaiUser(session.loginUser)}' class='cactions_cai' onclick='caiComment(this)'>"+
					"					<span th:text='${reply.caiUsers.size()}'></span>"+
					"				</a>"+
					"				<a class='cactions_hui' onclick='replyRecomment(this)' th:attr='style='background:url('+@{/images/comment_reply_hui.png}+') no-repeat''>"+
					"					<span>回复</span>"+
					"				</a>"+
					"			</div>"+
					"		</div>"+
					"	</div>"+
					"</li>");
				var $csize = $reclist.prev(".csize");
				$csize.find("em").text(++cSize);
				if(cSize > 5){
					$csize.append("<a class='rec_show_hide' onclick='showorhideRecomment(this)'>收起</a>");
				}
				cancelReplyComment();
			} else {
				alert("回复失败");
				return;
			}
		}
	});
}

/*ajax删除评论*/
function ajax_dc(url_ul_selector, id, $csize, $li, $ul){
	$.ajax({
		type:"get",
		url:"/blog/"+ url_ul_selector +"/"+ id +"/delete",
		success:function(data){
			if(data.msg.trim() == "success"){
				var cSize = parseInt($csize.find("em").text());
				cSize--;
				if(cSize <= 0){
					$ul.html(" ");
					$ul.prev().remove();
				}else{
					$csize.find("em").text(cSize);
					$li.slideUp(function(){
						$(this).remove();
						if(cSize <= 5){
							$csize.find("a").remove();
						}
					});
				}
			}else{
				alert("删除失败");
				return;
			}
		}
	});
}

/*点击显示回复评论的输入框*/
function showReplyCommentEditor($after) {
	$("<div id='receditor_container'>"+
		"<textarea id='receditor'></textarea>"+
		"<p id='receditor_actions'>"+
		"	<input id='receditor_actions_confirm' onclick='confirmReplyComment(this)' type='button' value='确认'/>"+
		"	<input id='receditor_actions_cancel' onclick='cancelReplyComment(this)' type='button' value='取消'/>"+
		"</p>"+
	"</div>").insertAfter($after);
	reply_btn_flag = 1;
	$("#receditor").focus();								
}

/*点击显示回复再评论的输入框*/
function showReplyRecommentEditor($after){
	$("<div id='receditor_container'>"+
			"<textarea id='receditor'></textarea>"+
			"<p id='receditor_actions'>"+
			"	<input id='receditor_actions_confirm' onclick='confirmReplyRecomment(this)' type='button' value='确认'/>"+
			"	<input id='receditor_actions_cancel' onclick='cancelReplyComment(this)' type='button' value='取消'/>"+
			"</p>"+
		"</div>").insertAfter($after);
	reply_btn_flag = 1;
	$("#receditor").focus();
}

/*确认回复评论*/
function confirmReplyComment(obj) {
	var $this = $(obj);
	var $citem = $this.parents(".citem");
	var commentId = $citem.data("id");
	var replyTo = $citem.find(".cuser_name").html();
	var replyToId = $citem.find(".cuser_name").data("id");
	var recHtml = $("#receditor").val();
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	if(isEmpty(recHtml)){
		alert("请输入内容");
		$("#receditor").focus();
		return;
	}
	//wangEditor富文本编辑器清空内容是：用<p><br/></p>覆盖
	recHtml = "<p>" + recHtml +"</p>";
	ajax_pc($citem.find(".recomment_container .reclist"), commentId, user_id, replyToId, replyTo, recHtml);
	
}

/*确认回复再评论*/
function confirmReplyRecomment(obj){
	var $this = $(obj);
	var $recitem = $this.parents(".recitem");
	var commentId = $this.parents(".citem").data("id");
	var replyTo = $recitem.find(".cuser_name").html();
	var replyToId = $recitem.find(".cuser_name").data("id");
	var recHtml = $("#receditor").val();
	if(isEmpty(user_id)){
		alert("请先登录");
		return;
	}
	if(isEmpty(recHtml)){
		alert("请输入内容");
		$("#receditor").focus();
		return;
	}
	//wangEditor富文本编辑器清空内容是：用<p><br/></p>覆盖
	recHtml = "<p>" + recHtml +"</p>";
	ajax_pc($this.parents(".reclist"), commentId, user_id, replyToId, replyTo, recHtml);
	
}

/*取消回复评论*/
function cancelReplyComment() {
	$("#receditor_container").slideUp(function(){
		$(this).remove();
		reply_btn_flag = 0;
	});
}

/*删除评论*/
function deleteComment(obj) {
	if(window.confirm("确认删除？")){
		var $this = $(obj);
		var cid = $this.parents(".citem").data("id");
		ajax_dc("comments", cid, $("#csize"), $this.parents(".citem"), $("#clist"));
	}
}

/*删除再评论*/
function deleteReComment(obj){
	if(window.confirm("确认删除？")){
		var $this = $(obj);
		var recid = $this.parents(".recitem").data("id");
		var $reclist = $this.parents(".reclist");
		ajax_dc("replies", recid, $reclist.prev(".csize"), $this.parents(".recitem"), $reclist);
	}
}


/*sidebar部分开始*/

/*sidebar部分结束*/

