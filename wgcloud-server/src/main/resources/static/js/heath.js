function add() {
    window.location.href = "/wgcloud/heathMonitor/edit";
}

function view(id) {
    window.location.href = "/wgcloud/heathMonitor/view?id=" + id;
}

function del(id) {
    if (confirm('你确定要删除吗？')) {
        window.location.href = "/wgcloud/heathMonitor/del?id=" + id;
    }
}

function edit(id) {
    window.location.href = "/wgcloud/heathMonitor/edit?id=" + id;
}

function cancel() {
    history.back();
}
