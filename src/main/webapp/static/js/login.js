//$(document).ready(function () {
//    $(".login").on("click", function(e){
//        e.preventDefault();
//        console.log("click on login");
//        $('#login-modal').fadeIn()
//        $('.modal').modal('hide');
//        $('#login-modal').modal('show');
//    });
//
//    $('#loginForm').on("submit", function (e) {
//        e.preventDefault();
//        let email = $('#email').val();
//        let password = $('#password').val();
//        console.log(email)
//        console.log(password)
//        $.ajax({
//            type: "POST",
//            url: "login",
//            data: {"email": email, "password": password},
//            success: function (data) {
//                console.log(data)
//                if (data.success) {
//                    $(location).attr('href', data.url)
//                } else {
//                    let textNode = document.createTextNode(data.message);
//                    $('.login.message').append(textNode);
//                }
//
//            }
//        })
//    })
//})

$(document).on("click", ".login", function(e) {  // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    e.preventDefault();
    let login = $('#email').val();
    let password = $('#password').val();

    $.ajax({
        type: "POST",
        url: "login-validation",
        data: {"email": login, "password": password},
        success: function (data) {
            console.log(data);
            if (data.success) {
                $(location).attr('href', data.url)
            } else {
                $('.error-message').text(data.message);
            }

        }
    })
});