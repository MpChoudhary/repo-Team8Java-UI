<%--
  Created by IntelliJ IDEA.
  User: tau
  Date: 2019-08-04
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Feature</title>
</head>
<body>
    <form action="saveFeature" method="post">
        <label for="fieldName">Field</label>
        <br>
        <input type="text" name="fieldName" id="fieldName">
        <br>
        <label for="selectType">Type</label>
        <br>
        <select name="selectType" id="selectType">
            <option value="number">Number</option>
            <option value="type">Type</option>
            <option value="text">Text</option>
            <option value="formula">Formula</option>
        </select>
        <br>
        <input type="submit">
    </form>
</body>
</html>
