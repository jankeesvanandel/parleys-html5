function handlePossibleTimeout(data) {
    if (data.responseCode == "500") {
        alert("Your session has ended. You are now redirected to the homepage");
        document.location = "http://ipad.parleys.com";
    }
}
