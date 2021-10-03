function view(id) {
    window.location.href = "/wgcloud/appInfo/view?id=" + id;
}

function del(id) {
    window.location.href = "/wgcloud/appInfo/del?id=" + id;
}

function cancel() {
    history.back();
}