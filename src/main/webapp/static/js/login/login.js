

$(document).ready(function(){
       $("#userName").focus();	
});

function login(){
	var user=$("#userName").val();
	var passwd=$("#passwd").val();
	var code=$("#code").val();
	if(user==''||passwd==''||code==''){
		alert("请输入必填项");
		return false;
	}else{
		$("#md5pwd").val(hex_md5($.trim($("#passwd").val())));
		$("#passwd").val('');
		return true;
	}
}


function changeImg() {
	var imgSrc = $("#imgObj");
	var url = imgSrc.attr("src");
	var timestamp = (new Date()).valueOf();
	if ((url.indexOf("?") != -1)) {
		url = url.substring(0,url.indexOf("=")+1)+timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	imgSrc.attr("src", url);
};

