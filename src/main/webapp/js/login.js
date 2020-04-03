$(document).ready(function () {
    //通过验证的标志,只有通过验证才能进行注册
    var username_flag;
    var password_flag;
    var password2_flag;
    var phone_flag;
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
                        //自动关闭模态框
                        $("#modal").modal("hide");
                        //给出"登录成功"提示
                        new jBox('Notice', {
                            attributes: {
                                x: 'right',
                                y: 'top'
                            },
                            stack: false,
                            animation: {
                                open: 'tada',
                                close: 'zoomIn'
                            },
                            color: "green",
                            content: '恭喜您,登录成功了哦!'
                        });
                        $("#login-div-1").hide();
                        $("#login_span").html(result.user.userName);
                        $("#login-div-2").show();
                    } else {
                        alert("账号或密码错误!");
                    }
                }
            });
        }
    });

    //注册表单验证
    $("#btn-rg").click(function () {
        // 注册表单数据
        if (username_flag == 1 && password_flag == 1 && password2_flag == 1 && phone_flag == 1) {
            //所有验证都通过,向服务器发送注册信息
            var username = $("#username-rg").val();
            var password = $("#password-rg").val();
            var phone = $("#phone-rg").val();
            $.ajax({
                url: "login/regist",
                data: {"username":username,"password":password,"phone":phone},
                dataType: "json",
                type: "post",
                success: function (result) {
                    alert(result.msg);
                }
            });
        } else {
            //注册失败
            alert("注册失败!请仔细检查!");
        }


    });

    //注册用户名验证
    $("#username-rg").change(function () {
        var username = $("#username-rg").val();
        var user_span = $("#username-rg-sp");
        var username_rule = /^[\u4E00-\u9FA5A-Za-z0-9_]{2,10}$/;
        if (username == "") {
            user_span.css("color", "red");
            user_span.html("*&nbsp;&nbsp;用户名不能为空");
        } else {
            if (!username_rule.test(username)) {
                user_span.css("color", "red");
                user_span.html("*&nbsp;&nbsp;用户名只能由字母.数字和汉字组成哦");
            } else {
                $.ajax({
                    url: "login/findUserByName",
                    data: {"username": username},
                    dataType: "json",
                    type: "post",
                    success: function (result) {
                        if (result.isExsit) {//用户名不存在,输入的用户名可以用
                            username_flag = 1;
                            user_span.css("color", "green")
                            user_span.html(result.msg);
                        } else {            //用户名已存在,输入的用户名不可用
                            user_span.css("color", "red")
                            user_span.html(result.msg);
                        }
                    }
                });
            }
        }
    });

    //注册密码验证
    $("#password-rg").change(function () {
        var password = $("#password-rg").val();
        var password_span = $("#password-rg-sp");
        var password_rule = /^[a-zA-Z0-9]{4,18}$/;
        if (password == "") {
            password_span.css("color", "red");
            password_span.html("*&nbsp;&nbsp;密码不能为空");
        } else {
            if (!password_rule.test(password)) {
                password_span.css("color", "red");
                password_span.html("*&nbsp;&nbsp;密码只能由4~18位字母和数字组成");
            } else {
                password_flag = 1;
                password_span.css("color", "green");
                password_span.html("*&nbsp;&nbsp;密码格式正确");
            }
        }
    });

    //注册确认密码验证
    $("#password2-rg").change(function () {
        var password = $("#password-rg").val();
        var password2 = $("#password2-rg").val();
        var password2_span = $("#password2-rg-sp");
        if (password == password2) {
            password2_flag = 1;
            password2_span.css("color", "green");
            password2_span.html("*&nbsp;&nbsp;两次密码输入一致");
        } else {
            password2_span.css("color", "red");
            password2_span.html("*&nbsp;&nbsp;两次密码输入不一致,请再次检查");
        }
    });

    //注册手机号码验证
    $("#phone-rg").change(function () {
        var phone = $("#phone-rg").val();
        var phone_span = $("#phone-rg-sp");
        var phone_rule = /^1\d{10}$/;
        if (phone == "") {
            phone_span.css("color", "red");
            phone_span.html("*&nbsp;&nbsp;手机号码不能为空");
        } else {
            if (!phone_rule.test(phone)) {
                phone_span.css("color", "red");
                phone_span.html("*&nbsp;&nbsp;手机号码格式不正确");
            } else {
                $.ajax({
                    url: "login/findUserByPhone",
                    data: {"phone": phone},
                    dataType: "json",
                    type: "post",
                    success: function (result) {
                        if (result.isExsit) {//此手机号码不存在,输入的手机号码可以用
                            phone_flag = 1;
                            phone_span.css("color", "green");
                            phone_span.html(result.msg);
                        } else {            //此手机号码已存在,输入的手机号码不可用
                            phone_span.css("color", "red");
                            phone_span.html(result.msg);
                        }
                    }
                });
            }
        }
    });
});
