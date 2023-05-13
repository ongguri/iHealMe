/*
let index= {
    init: function (){
        $(".registerUser").on("click", () => {
            this.userSave();
        });
    },

    userSave: function (){
        let data = {
            name: $("#name").val(),
            email: $("#email").val(),
            phoneNum: $("#phoneNum").val(),
            birthDate: $("#birthDate").val(),
            gender: $("#gender").val(),
            password: $("#password").val(),
            confirmPassword: $("#confirmPassword").val(),
            question: $("#question").val(),
            answer: $("#answer").val()
        };

        $.ajax({
            url: `/registeruser`,
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
        }).done(function (resp){
            alert("회원가입이 완료되었습니다.")
            location.href = `/login`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();*/

$(document).ready(function () {
    function userSave() {
        let data = {
            name: $("#name").val(),
            email: $("#email").val(),
            phoneNum: $("#phoneNum").val(),
            birthDate: $("#birthDate").val(),
            gender: $("#gender").val(),
            password: $("#password").val(),
            confirmPassword: $("#confirmPassword").val(),
            question: $("#question").val(),
            answer: $("#answer").val()
        };

        $.ajax({
            url: `/registeruser`,
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
        }).done(function (resp) {
            alert("회원가입이 완료되었습니다.")
            location.href = `/login`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
});
