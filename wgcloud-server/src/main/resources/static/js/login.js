$(document).ready(function () {
    $("#userName").focus();
    $("#form1").validationEngine();
});


function setMd5Pwd() {
    $("#md5pwd").val(hex_md5($.trim($("#passwd").val())));
    $("#passwd").val('********');
    return true;
}


