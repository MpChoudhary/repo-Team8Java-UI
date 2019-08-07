<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springproject.entity.Project" %>
<%@ page import="springproject.entity.Feature" %>
<%@ page import="java.util.List" %>
<% List<Project> projects = (List<Project>) request.getAttribute("projects"); %>
<% List<Feature> features = (List<Feature>) request.getAttribute("features"); %>
<% Project displaySelectedProject = (Project)request.getAttribute("displaySelectedProject"); %>
<html>
<head>
    <title>Feature</title>
</head>
<body>
    <a href="home">Home</a>
    <%-- Add new feature(Column) --%>
    <h1>New feature</h1>
    <form action="FeatureHandler" method="post">
        <label for="projectName">Project</label>
        <select name="projectName" id="projectName">
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <br>
        <label for="featureName">feature name</label>
        <input type="text" id="featureName" name="featureName">

        <label for="featureType">Type</label>
        <select name="featureType" id="featureType">
            <option value="number">Number</option>
            <option value="text">Text</option>
        </select>

        <label for="featureContent">Content</label>
        <input type="text" id="featureContent" name="featureContent">
        <input type="submit" name="submit" value="newFeature">
    </form>

    <hr>

    <%-- Display selected project feature --%>
    <form action="FeatureHandler" method="post">
        <label for="displaySelectedProject">Project</label>
        <select name="projectName" id="displaySelectedProject">
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <button type="submit" name="submit" value="displayFeature">Display</button>
    </form>
    <h2><%= displaySelectedProject.getName() %></h2>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type</th>
            <th>Content</th>
        </tr>
        <% for(Feature feature : features) {
            if (feature.getProject().getId() == displaySelectedProject.getId()) { %>
        <tr>
            <td><%= feature.getId() %></td>
            <td><%= feature.getName() %></td>
            <td><%= feature.getType() %></td>
            <td><%= feature.getContent() %></td>
            <td>
                <form action="FeatureHandler" method="post">
                    <button type="submit" name="submit" value="delete<%=feature.getId()%>">Delete</button>
                </form>
            </td>
        </tr>
        <%      }
        }   %>

    </table>
</body>
</html>
