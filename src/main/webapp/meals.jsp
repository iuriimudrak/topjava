<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://topjava.ru" prefix="frm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<br>
<h1>Meals</h1>
<br>
<a href="index.html">Home</a>
<br>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <c:forEach items="${meals}" var="meal">
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td>${frm:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd hh:mm')}</td>
            <td>${meal.calories}</td>
            <td>${meal.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>