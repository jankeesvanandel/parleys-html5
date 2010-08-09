function showInlineLoader() {
    $('img.inlineLoader').show();
}

function onPagingAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('img.inlineLoader').hide();
    }
}
