<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="50%">
		<tr>
			<td>id</td>
			<td>name</td>
			<td>age</td>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>