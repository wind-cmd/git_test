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
            text-align: center;
        }
    </style>

</head>
<body>
<div class="login">
    <form action="/Register"method="post">
        <div id="errorMsg">${register_msg}</div>
        <input type="text" name="userno" placeholder="学号" required><br><br>
        <input type="password" name="password" placeholder="密码" required><br><br>
        <input type="text" name="checkCode" placeholder="请输入验证码"><img id="checkCodeImg" src="/CheckCode"><a id="changeImg" href="#">换一张</a>
        <input type="submit" value="注册">
    </form>
</div>
</body>
<script>
    document.getElementById("changeImg").addEventListener("click",function(){
        document.getElementById("checkCodeImg").src="/CheckCode?"+new Date().getMilliseconds();
    })

</script>
</html>