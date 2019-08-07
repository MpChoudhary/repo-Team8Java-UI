<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springproject.entity.Project" %>
<%@ page import="java.util.List" %>
<html>
<head>
	<title>Project</title>
</head>
<body>
	<a href="home">Home</a>
	<h1>New Project</h1>
	<form action="ProjectHandler" method="post">
		<label for="projectName">Project name</label>
		<input type="text" id="projectName" name="projectName">
		<input type="submit" value="newProject">
	</form>
	<hr>
	<h2>Project List:</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
		</tr>
		<% List<Project> projects = (List<Project>)request.getAttribute("projects");
			for(Project project : projects) { %>
		<tr>
			<td><%= project.getId() %></td>
			<td><%= project.getName() %></td>
			<td>
				<form action="ProjectHandler" method="post">
					<button type="submit" name="submit" value="delete<%= project.getId()%>">Delete</button>
				</form>
			</td>
		</tr>
		<%	} %>
	</table>
</body>
</html>
