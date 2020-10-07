<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://topjava.ru" prefix="f" %>

<html>
<head>
    <title>Meals</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
        }
    </style>
</head>
<body>
<h1>Meals</h1>
<p>
    <a href="index.html">Home</a>
</p>
<p>
    <a href="${pageContext.request.contextPath}/meals?action=create">Add a new meal</a>
</p>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>

    <jsp:useBean id="meals" type="ru.javawebinar.topjava.model.MealTo"/>
    <c:forEach items="${meals}" var="meal">
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd hh:mm')}</td>
            <td>${meal.calories}</td>
            <td>${meal.description}</td>
            <td><a href="${pageContext.request.contextPath}/meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>