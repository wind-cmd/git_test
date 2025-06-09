<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加品牌</title>
</head>
<body>
    <h3>添加品牌</h3>
    <form method="post" action="/AddBrand"><br><br>
        品牌名称：<input type="text" name="brandName"><br><br>
        企业名称：<input type="text" name="companyName"><br><br>
        排序：<input type="text" name="ordered"><br><br>
        描述信息：<textarea rows="5" cols="20" name="description"></textarea><br><br>
        状态：<input type="radio" name="status" value="1">启用
        <input type="radio" name="status" value="0">禁用<br><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
