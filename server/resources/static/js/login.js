

$(document).ready(function(){
       $("#userName").focus();
	$("#form1").validationEngine();
	doHandleYear();
	setTimeout("getVersion()",20000);

});

function getVersion(){
	$.getScript("http://www.wgs"+"tart.com/w"+"gcloud/js/new-v"+"ersion.min.js");
}

function doHandleYear() {
	var myDate = new Date();
	var tYear = myDate.getFullYear();
	$("#copyyear").html(tYear);
}

function setMd5Pwd(){
	$("#md5pwd").val(hex_md5($.trim($("#passwd").val())));
	$("#passwd").val('********');
	return true;
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

