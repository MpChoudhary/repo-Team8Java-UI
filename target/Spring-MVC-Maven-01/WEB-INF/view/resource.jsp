<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springproject.entity.Project" %>
<%@ page import="springproject.entity.Resource" %>
<%@ page import="java.util.List" %>
<% List<Project> projects = (List<Project>) request.getAttribute("projects"); %>
<% List<Resource> resources = (List<Resource>) request.getAttribute("resources"); %>
<% Project displaySelectedProject = (Project)request.getAttribute("displaySelectedProject"); %>
<html>
<head>
    <title>Resource</title>
</head>
<body>
    <a href="home">Home</a>
    <%-- Add new resource--%>
    <h1>New Resource</h1>
    <form action="ResourceHandler" method="post">
        <label for="projectName">Project</label>
        <select name="projectName" id="projectName">
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <br>
        <label for="resourceName">Resource name</label>
        <input type="text" id="resourceName" name="resourceName">

        <label for="resourceCode">Resource code</label>
        <input type="text" id="resourceCode" name="resourceCode">
        <input type="submit" name="submit" value="newResource">
    </form>

    <hr>

    <%-- Display selected project resources --%>
    <form action="ResourceHandler" method="post">
        <label for="displaySelectedProject">Project</label>
        <select name="projectName" id="displaySelectedProject">
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <button type="submit" name="submit" value="displayResource">Display</button>
    </form>
    <h2><%= displaySelectedProject.getName() %></h2>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Code</th>
        </tr>
        <% for(Resource resource : resources) {
               if (resource.getProject().getId() == displaySelectedProject.getId()) { %>
                    <tr>
                        <td><%= resource.getId() %></td>
                        <td><%= resource.getName() %></td>
                        <td><%= resource.getCode() %></td>
                        <td>
                            <form action="ResourceHandler" method="post">
                                <button type="submit" name="submit" value="delete<%= resource.getId() %>">Delete</button>
                            </form>
                        </td>
                    </tr>
        <%      }
         }   %>

    </table>
</body>
</html>
