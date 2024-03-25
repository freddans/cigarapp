$(document).ready(function() {
    // Fetch data from the backend when the page loads
    $.get("/data", function(data) {
        console.log(data);
    });
});