function gotoThumbnails(id) {
    try {
        $('#thumbnailsWrapper').fadeOut('fast', function() {
            jsf.ajax.request(id, "event", {
                render: 'main:thumbnails',
                onevent: onThumbnailsEvent,
                'javax.faces.behavior.event': 'action'
            });
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onThumbnailsEvent(evt) {
    if (evt.status == 'complete') {
        $('#thumbnailsWrapper').fadeIn('fast');
    }
}
