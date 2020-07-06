<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>用户列表页面</title>
	</head>
	<body>
		<table width="60%" algin="center" border="1">
			<tr align="center">
				<td colspan="5"><h2>用户信息</h2></td>
			</tr>
			<tr align="center">
				<td>序号</td>
				<td>ID</td>
				<td>姓名</td>
				<td>年龄</td>
				<td>性别</td>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr align="center">
				<td>${status.index+1}</td>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
				<td>${user.sex}</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>