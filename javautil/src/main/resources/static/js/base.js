$("#avatar").mouseover(function(){
	$("#user_list").show();
});
$("#user").mouseleave(function(){
	$("#user_list").hide();
});


var user_id = $("#user_id").data("id");


//判断字符串是否为空
function isEmpty(str){
	if (str == '' || str == null || str == undefined) {
		return true;
	} else {
		return false;
	}
}

function getLocalTime(time) {     
	var date = new Date(time);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() < 10 ? '0'+date.getDate()+' ' : date.getDate() + ' ';
	h = date.getHours() < 10 ? '0'+date.getHours()+':' : date.getHours() + ':';
	m = date.getMinutes() < 10 ? '0'+date.getMinutes()+':' : date.getMinutes();
	//s = date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds(); 
	return(Y+M+D+h+m); 
}
