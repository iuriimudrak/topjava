<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${param.action.equals("create") ? "Create new meal" : "Update current meal"}</title>
    <style>
        textarea, input {
            width: 200px;
        }

        textarea {
            height: 100px;
        }
    </style>
</head>
<body>
<h1>${param.action.equals("create") ? "Create new meal" : "Update current meal"}</h1>
<p><a href="${pageContext.request.contextPath}/meals?action=read">Back</a></p>
<form action="${pageContext.request.contextPath}/meals" method="post">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td><label for="description">Description:</label></td>
            <td><textarea id="description" name="description" required>${meal.description}</textarea></td>
        </tr>
        <tr>
            <td><label for="date">Date:</label></td>
            <td><input id="date" type="datetime-local" name="date" value="${meal.dateTime}"/></td>
        </tr>
        <tr>
            <td><label for="calories">Calories:</label></td>
            <td><input id="calories" type="number" name="calories" value="${meal.calories}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>