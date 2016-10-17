/*加关注*/
function careUser(obj) {
	if (isEmpty(user_id)) {
		alert("请先登录");
		return;
	}

	$.ajax({
		type : "post",
		url : "/blog/users/" + user_id + "/care/" + blog_user_id,
		success : function(data) {
			if (data.trim() == "success") {
				$(obj).addClass("visited no_hover").text("已关注").unbind(
						"click");
				return;
			} else {
				return;
			}
		}
	});
}

/* 取消关注 */
function cancelCareUser(obj) {
	// var userId = /*[[${session.loginUser.id}]]*/ null;
	var userId = $("#user_id").data("id");
	var caredUserId = $(obj).data("id");
	$.ajax({
		type : "get",
		url : "/blog/users/" + userId + "/cancelCare/" + caredUserId,
		success : function(data) {
			if (data.trim() == "success") {
				$(obj).parents(".user").slideUp();
				return;
			} else {
				return;
			}
		}
	});
}

function updatePassword(obj) {
	var oldPwd = $("#old_password").val();
	var newPwd = $("#new_password").val();
	var reNewPwd = $("#re_new_password").val();
	if (isEmpty(oldPwd) || isEmpty(newPwd) || isEmpty(reNewPwd)) {
		alert("请将密码修改表单填写完整");
		return;
	}
	if (newPwd.length < 6) {
		alert("新密码长度小于6位");
		return;
	}
	if (newPwd != reNewPwd) {
		alert("两次输入的密码不一致");
		return;
	}
	$.ajax({
		type : "post",
		url : "/blog/users/" + user_id + "/updatePassword",
		data : {
			"oldPassword" : oldPwd,
			"newPassword" : newPwd
		},
		success : function(data) {
			if (data.trim() == "success") {
				$("#old_password").val("");
				$("#new_password").val("");
				$("#re_new_password").val("");
				alert("密码修改成功");
			} else {
				alert("密码修改失败");
			}
		}
	});

}

function deleteUser_index(obj){
		if(window.confirm("确认删除？")){
			var $this = $(obj);
			var userId = $this.data("id");
			$.ajax({
				type:"get",
				url:"/blog/users/"+userId+"/delete",
				success:function(data){
					if(data.msg.trim()=="success"){
						$this.parents(".user").slideUp();
						return;
					}
				}
			});
		}
	}

/*管理员删除用户账号*/
function deleteUser_show(obj) {
	if(window.confirm("确认删除？")){
		var userId = $(obj).data("id");
		$.ajax({
			type : "get",
			url : "/blog/users/" + userId + "/delete",
			success : function(data) {
				if (data.msg.trim() == "success") {
					window.location.href = "/blog/admins/adminUsers";
					return;
				}
			}
		});
	}
}