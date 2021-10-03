$(document).ready(function () {
    $("#userName").focus();
    $("#form1").validationEngine();
    setTimeout("getVersion()", 1000);
});


function setMd5Pwd() {
    $("#md5pwd").val(hex_md5($.trim($("#passwd").val())));
    $("#passwd").val('********');
    return true;
}


function changeImg() {
    var imgSrc = $("#imgObj");
    var url = imgSrc.attr("src");
    var timestamp = (new Date()).valueOf();
    if ((url.indexOf("?") != -1)) {
        url = url.substring(0, url.indexOf("=") + 1) + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    imgSrc.attr("src", url);
};

