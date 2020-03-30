$(document).ready(function () {

    //登录时的验证
    $("#btn-lg").click(function () {
        var username = $("#username-lg").val();
        var password = $("#password-lg").val();
        var user_span = $("#username-lg-sp");
        var pwd_span = $("#password-lg-sp");
        // 用户名/手机号验证
        if (username == ""){
            user_span.html("*&nbsp;&nbsp;用户名/手机号不能为空");
            user_span.css("color","red");
            user_span.show();
        }
        // 密码验证
        if (password == ""){
            pwd_span.html("*&nbsp;&nbsp;密码不能为空");
            pwd_span.css("color","red");
            pwd_span.show();
        }
        // 检查登录是否成功
        if (username != "" && password != ""){
            $.ajax({
                url : "login/findUserByIdAndPwd",
                data:{"username": username,"password":password},
                dataType:"json",
                type:"post",
                success:function (result) {
                    if (result.isExsit){
                        alert("登录成功!")
                    } else {
                        alert("账号或密码错误!");
                    }
                }
            });
        }
    });

    //注册时的验证
    $("#btn-rg").click(function () {
        //注册表单数据
        var username = $("#username-rg").val();
        var password = $("#password-rg").val();
        var password2 = $("#password2-rg").val();
        var phone = $("#phone-rg").val();
        var number = $("#number-rg").val();

        //注册表单提示内容
        var user_span = $("#username-rg-sp");
        var pwd_span = $("#password-rg-sp");
        var pwd2_span = $("#password2-rg-sp");
        var phone_span = $("#phone-rg-sp");
        var number_span = $("#number-rg-sp");
        // 用户名验证
        if (username == ""){
            user_span.html("*&nbsp;&nbsp;用户名不能为空");
            user_span.css("color","red");
            user_span.show();
        }
        // 密码验证
        if (password == ""){
            pwd_span.html("*&nbsp;&nbsp;密码不能为空");
            pwd_span.css("color","red");
            pwd_span.show();
        }
        // 确认密码验证
        if (password2 == ""){
            pwd2_span.html("*&nbsp;&nbsp;确认密码不能为空");
            pwd2_span.css("color","red");
            pwd2_span.show();
        }
        // 手机号验证
        if (phone == ""){
            phone_span.html("*&nbsp;&nbsp;手机号不能为空");
            phone_span.css("color","red");
            phone_span.show();
        }
        // 验证码验证
        if (number == ""){
            number_span.html("*&nbsp;&nbsp;验证码不能为空");
            number_span.css("color","red");
            number_span.show();
        }
    });
});