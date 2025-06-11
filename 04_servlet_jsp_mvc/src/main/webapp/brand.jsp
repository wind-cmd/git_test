
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP示例页面</title>
</head>
<body>
<h1>${user.userNo}，欢迎您</h1>
<!-- 显示当前时间 -->
<p>当前时间是: <%= new java.util.Date() %>
</p>
<input id="add" type="button"value="添加">
<table align="center" border="1px">
    <tr>
        <td><h2>序号</h2></td>
        <td><h2>品牌名称</h2></td>
        <td><h2>企业名称</h2></td>
        <td><h2>排序</h2></td>
        <td><h2>品牌介绍</h2></td>
        <td><h2>状态</h2></td>
        <td><h2>操作</h2></td>
    </tr>
    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status==1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status==0}">
                <td>禁用</td>
            </c:if>
            <td><a href="/SelectById?id=${brand.id}">修改</a> <a onclick="deleteById(${brand.id})">删除</a></td>
        </tr>
    </c:forEach>
</table>
<script>
    document.getElementById("add").addEventListener("click",function (){
        location.href="/addBrand.jsp"
    });
    function deleteById(id){
        if (confirm("确定要删除这个品牌吗？")) {
            window.location.href = "/DeleteById?id=" + id;
        }
    }
</script>
</body>
</html>