<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Testing </title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Testing OneToMany</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<table>
				<tr>
					<th>Name</th>
				</tr>
				<c:forEach var="tempProject" items="${projects}">
					<tr>
						<td>${tempProject.name}</td>
					</tr>
				</c:forEach>
			</table>

			<table>
				<tr>
					<th>Name</th>
					<th>Code</th>
				</tr>
				<c:forEach var="tempResource" items="${resources}">
					<tr>
						<td>${tempResource.code}</td>
						<td>${tempResource.name}</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>
</body>
</html>
