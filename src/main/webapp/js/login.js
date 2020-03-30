$(document).ready(function () {

    //登录时的验证
    $("#btn-lg").click(function () {
        var username = $("#username-lg").val();
        var password = $("#password-lg").val();
        var user_span = $("#username-lg-sp");
        var pwd_span = $("#password-lg-sp");
        // 用户名/手机号验证
        if (username == "") {
            user_span.html("*&nbsp;&nbsp;用户名/手机号不能为空");
            user_span.css("color", "red");
            user_span.show();
        }
        // 密码验证
        if (password == "") {
            pwd_span.html("*&nbsp;&nbsp;密码不能为空");
            pwd_span.css("color", "red");
            pwd_span.show();
        }
        // 检查登录是否成功
        if (username != "" && password != "") {
            $.ajax({
                url: "login/findUserByIdAndPwd",
                data: {"username": username, "password": password},
                dataType: "json",
                type: "post",
                success: function (result) {
                    if (result.isExsit) {
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
        // if (username == "") {
        //         //     user_span.html("*&nbsp;&nbsp;用户名不能为空");
        //         //     user_span.css("color", "red");
        //         //     user_span.show();
        //         // }
        // 密码验证
        if (password == "") {
            pwd_span.html("*&nbsp;&nbsp;密码不能为空");
            pwd_span.css("color", "red");
            pwd_span.show();
        }
        // 确认密码验证
        if (password2 == "") {
            pwd2_span.html("*&nbsp;&nbsp;确认密码不能为空");
            pwd2_span.css("color", "red");
            pwd2_span.show();
        }
        // 手机号验证
        if (phone == "") {
            phone_span.html("*&nbsp;&nbsp;手机号不能为空");
            phone_span.css("color", "red");
            phone_span.show();
        }
        // 验证码验证
        if (number == "") {
            number_span.html("*&nbsp;&nbsp;验证码不能为空");
            number_span.css("color", "red");
            number_span.show();
        }
    });

    //注册时验证用户名是否已存在
    $("#username-rg").blur(function () {
        var username = $("#username-rg").val();
        var user_span = $("#username-rg-sp");
        var username_rule = "/^\w+$/";
        if (username == ""){
            user_span.css("color", "red");
            user_span.html("*&nbsp;&nbsp;用户名不能为空");
        }else if (username_rule.test(username)) {
            user_span.css("color", "red");
            user_span.html("*&nbsp;&nbsp;用户名格式不正确,只能由字母.数字.下划线组成哦");
        }else {
            $.ajax({
                url: "login/findUserByName",
                data: {"username": username},
                dataType: "json",
                type: "post",
                success:function (result) {
                    if (result.isExsit){//用户名不存在,输入的用户名可以用
                        user_span.css("color","green")
                        user_span.html(result.msg);
                    } else {            //用户名已存在,输入的用户名不可用
                        user_span.css("color","red")
                        user_span.html(result.msg);
                    }
                }
            });
        }
    });

    //注册时验证手机号是否已存在
    $("#phone-rg").blur(function () {
        var phone = $("#phone-rg").val();
        var phone_span = $("#phone-rg-sp");
        $.ajax({
            url: "login/findUserByPhone",
            data: {"phone": phone},
            dataType: "json",
            type: "post",
            success:function (result) {
                if (result.isExsit){//此手机号码不存在,输入的手机号码可以用
                    phone_span.css("color","green")
                    phone_span.html(result.msg);
                } else {            //此手机号码已存在,输入的手机号码不可用
                    phone_span.css("color","red")
                    phone_span.html(result.msg);
                }
            }
        });
    });

    //注册时验证两次密码是否一致
    $("#password2-rg").blur(function () {
        var password = $("#password-rg").val();
        var password2 = $("#password2-rg").val();
        var password2_span = $("#password2-rg-sp");
        if (password == password2){
            password2_span.css("color","green");
            password2_span.html("两次密码输入一致");
        } else {
            password2_span.css("color","red");
            password2_span.html("两次密码输入不一致,请再次检查");
        }
    });
});