<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springproject.entity.Project" %>
<%@ page import="springproject.entity.Resource" %>
<%@ page import="springproject.entity.Feature" %>
<%@ page import="springproject.entity.FeatureValue" %>
<%@ page import="java.util.List" %>
<% List<Project> projects = (List<Project>) request.getAttribute("projects"); %>
<% List<Resource> resources = (List<Resource>) request.getAttribute("resources"); %>
<% List<Feature> features = (List<Feature>) request.getAttribute("features"); %>
<% List<FeatureValue> featureValues = (List<FeatureValue>) request.getAttribute("featureValues"); %>
<% Project displaySelectedProject = (Project)request.getAttribute("displaySelectedProject"); %>
<html>
<head>
    <title>Feature Values</title>
</head>
<body>
    <a href="home">Home</a>
    <%-- Add new feature value(Row) --%>
    <h1>New feature value</h1>
    <form action="FeatureValueHandler" method="post">
        <label for="projectName">Project</label>
        <select name="projectName" id="projectName" required>
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <select name="resourceName" id="resourceName" required>
            <% for(Resource resource : resources) {
                if(resource.getProject().getId() == displaySelectedProject.getId() && resource.getFeatureValue() == null) {%>
                    <option value="<%= resource.getId() %>"><%= resource.getName()%></option>
                <% }
            }%>
        </select>
        <select name="featureName" id="featureName" required>
            <% for(Feature feature : features) {
                if(feature.getProject().getId() == displaySelectedProject.getId()) { %>
                    <option value="<%= feature.getId() %>"><%= feature.getName()%></option>
                <% }
             } %>
        </select>
        <br>
        <label for="featureValue">feature value</label>
        <input type="text" id="featureValue" name="featureValue">

        <input type="submit" name="submit" value="newFeatureValue">
    </form>

    <hr>

    <%-- Display selected project feature --%>
    <form action="FeatureValueHandler" method="post">
        <label for="displaySelectedProject">Project</label>
        <select name="projectName" id="displaySelectedProject">
            <% for(Project project : projects) { %>
            <option value="<%= project.getId() %>"><%= project.getName()%></option>
            <% } %>
        </select>
        <button type="submit" name="submit" value="displayFeatureValue">Display</button>
    </form>
    <h2><%= displaySelectedProject.getName() %></h2>
    <%-- Resources --%>
    <h4>Resources</h4>
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
        </tr>
        <%      }
        }   %>

    </table>
    <hr>
    <%-- Features --%>
    <h4>Features</h4>
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
        </tr>
        <%      }
        }   %>

    </table>
    <hr>
    <%-- Feature Values --%>
    <h4>Feature Values</h4>
    <table>
        <tr>
            <th>Id</th>
            <th>Value</th>
            <th>Project</th>
            <th>Resource</th>
            <th>Feature</th>
        </tr>
        <% for(FeatureValue featureValue : featureValues) {
            if (featureValue.getProject().getId() == displaySelectedProject.getId()) { %>
        <tr>
            <td><%= featureValue.getId() %></td>
            <td><%= featureValue.getValue() %></td>
            <td><%= featureValue.getProject().getId() %></td>
            <td><%= featureValue.getResource().getId() %></td>
            <td><%= featureValue.getFeature().getId() %></td>
            <td>
                <form action="FeatureValueHandler" method="post">
                    <button type="submit" name="submit" value="delete<%= featureValue.getId() %>"></button>
                </form>
            </td>
        </tr>
        <%      }
        }   %>

    </table>
</body>
</html>
