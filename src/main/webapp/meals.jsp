<%--
  Created by IntelliJ IDEA.
  User: MsAnn
  Date: 10.02.2019
  Time: 2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<style>
    .not_excess{color:green}
    .excess{color: red}
</style>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table border="1">
    <caption>Список еды</caption>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Факт превышения</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr class=${meal.isExcess() ? 'excess':'not_excess'}>
            <td><fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
            <td>${meal.getDescription()}</td>
            <td>meal.getCalories()</td>
            <td>${meal.isExcess() ? 'превышение':''}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
