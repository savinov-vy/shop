$(document).ready(function () {
    $("#btnChekIn").click(function () {
        let name = $("#name").val();
        let password = $("#password").val();
        let confirmPassword = $("#confirmPassword").val();
        let fail ="";

        if (name.length < 3) fail = "Имя должно содержать не менее 3 символов";
        else if (password !== confirmPassword) fail = "Пароли не совпадают";
        if (fail != ""){
            $("#messageShow").html(fail + "<div class = 'clear'><br></div>");
            $("#messageShow").show();
            return false;
        }
    });
});