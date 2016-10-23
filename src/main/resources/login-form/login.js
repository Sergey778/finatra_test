
$("login-button").onclick(function (event) {
    var name = $("login-name").text();
    var pass = $("login-pass").text();
    $.ajax({
        url: "/login",
        type: "get",
        data: {loginName: name, loginPass: pass}
    });
});
