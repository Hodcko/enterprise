<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Result</title>
</head>
<body style="background-color: powderblue">
<h1 style="text-align: center"><c:out value="Ваш результат"/></h1>
<h2 style="text-align: center"><c:out value="${result}/5"/></h2>
<c:if test="${result > 4}">
    <h2 style="text-align: center"><c:out value="Поздравляю, вы получаете сертификат"/></h2>
</c:if>
<c:if test="${result < 5}">
    <h2 style="text-align: center"><c:out value="Вы получаете справку государственного образца"/></h2>
</c:if>
<hr>
<form action="${pageContext.request.contextPath}/start.jsp">
    <h3><c:out value="Перейти на главную страницу"/></h3>
    <input type="submit" value="Перейти">
</form>
    </body>
</html>
