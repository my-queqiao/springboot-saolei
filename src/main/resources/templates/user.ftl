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
		<#list userList as user>
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>200</td>
			</tr>
		</#list>
	</table>
</body>
</html>