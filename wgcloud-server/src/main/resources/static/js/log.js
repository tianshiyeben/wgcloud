function searchByPara() {
    var account = $("#account").val();
    window.location.href = "/wgcloud/log/list?account=" + escape(escape(account));
}

function view(id) {
    window.location.href = "/wgcloud/log/view?id=" + id;
}

function cancel() {
    history.back();
}
