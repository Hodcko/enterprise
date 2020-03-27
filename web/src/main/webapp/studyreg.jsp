<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Study registration</title>
</head>
<body>
<h2><c:out value="Для доступа к данному ресурсу вас неомходимо войти под своей учетной записью"/></h2>


</body>
</html>
<form action="${pageContext.request.contextPath}/personal.jsp">
    <h3><c:out value="Войти"/></h3>
    <input type="submit">
</form>
<form action="${pageContext.request.contextPath}/registration.jsp">
    <h3><c:out value="Зарегистрироваться"/></h3>
    <input type="submit">
</form>
