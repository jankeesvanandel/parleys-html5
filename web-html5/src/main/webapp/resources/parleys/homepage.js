function featuredContentEvent(id) {
    try {
        $('#thumbnailsWrapper').fadeOut('fast', function() {
            $('#thumbnailsWrapperOuter img.loader').show();
            jsf.ajax.request(id, "event", {
                render: 'main:thumbnails',
                onevent: onFeaturedContentAjaxEvent,
                'javax.faces.behavior.event': 'action'
            });
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onFeaturedContentAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('#thumbnailsWrapperOuter img.loader').hide();
        $('#thumbnailsWrapper').fadeIn('fast');
    }
}

function newsPaginationEvent(id) {
    try {
        $('#newsContainerWrapper').fadeOut('fast', function() {
            $('#newsContainerWrapperOuter img.loader').show();
            jsf.ajax.request(id, "event", {
                render: 'main:newsContainer',
                onevent: onNewsPaginationAjaxEvent,
                'javax.faces.behavior.event': 'action'
            });
        });
    } catch (e) {
        alert(e.message);
    }

    return false;
}

function onNewsPaginationAjaxEvent(evt) {
    if (evt.status == 'complete') {
        $('#newsContainerWrapperOuter img.loader').hide();
        $('#newsContainerWrapper').fadeIn('fast');
    }
}
