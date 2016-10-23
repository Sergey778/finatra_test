$(document).ready(function() {
    $("#login-form").submit(function (event) {
        var name = $("#login-name").val();
        var pass = $("#login-pass").val();
        $.ajax({
            url: "/login",
            type: "POST",
            data: {loginName: name, loginPass: pass},
            success: function (data) {
                window.location.replace("/index")
            },
            error: function (data) {
                $("#status-message").html("<p>Incorrect Password</p>");
            }
        });
        event.preventDefault();
    });

});