$(document).ready(function () {
    $("#btnChekIn").click(function () {
        var name = $("#name").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        var fail ="";

        // if (name.length < 3) fail = "Имя должно содержать не менее 3 символов";
        // else if (password !== confirmPassword) fail = "Пароли не совпадают";
        // if (fail != ""){
        //     $("#messageShow").html(fail + "<div class = 'clear'><br></div>");
        //     $("#messageShow").show();
        //     return false;
        // }
    });
});