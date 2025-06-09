<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <style>
        .login {
            width: 300px;
            margin: 100px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            align-content: center;
            text-align: center;
        }
    </style>

</head>
<body>
<div class="login">
    <form action="/Login"method="get">
        <h1>LOGIN IN</h1>
        <div id="errorMsg">${msg}${register_msg}</div>
        学号：<input type="text" name="userno" placeholder="学号" value="${cookie.userno.value}"required><br><br>
        密码：<input type="password" name="password" placeholder="密码" value="${cookie.password.value}" required><br><br>
        记住密码：<input type="checkbox" id="remember" name="remember" value="1"><br><br>
        <input type="submit" value="登录"><a href="/register.jsp">没有账号?点击注册</a>
    </form>
</div>
</body>
</html>
