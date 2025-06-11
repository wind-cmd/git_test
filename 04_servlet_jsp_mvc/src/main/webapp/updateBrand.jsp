<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>修改品牌</title>
</head>
<body>
<h3>修改品牌</h3>
<form method="post" action="/UpdateBrand"><br><br>
    <input type="hidden"name="id"value="${brand.id}">
    品牌名称：<input type="text" name="brandName" value="${brand.brandName}"><br><br>
    企业名称：<input type="text" name="companyName" value="${brand.companyName}"><br><br>
    排序：<input type="text" name="ordered" value="${brand.ordered}"><br><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br><br>
    状态：
    <c:if test="${brand.status == 0}">
        <input type="radio" name="status" value="1">启用
        <input type="radio" name="status" value="0" checked="checked">禁用<br><br>
    </c:if>
    <c:if test="${brand.status == 1}">
        <input type="radio" name="status" value="1" checked="checked">启用
        <input type="radio" name="status" value="0">禁用<br><br>
    </c:if>
    <input type="submit" value="提交">
</form>
</body>
</html>
