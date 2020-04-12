<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>First Jsp</title>

</head>
<body style="background-color: powderblue">


<h1 style="text-align: center"><c:out value="Не удалось войти в личный кабинет"/></h1>

<h2 style="text-align: center"><c:out value="Логин или пароль неверный"/></h2>

</body>
</html>

<form style="text-align: center" action="${pageContext.request.contextPath}/PersonalStart.jsp">
    <h3><c:out value="Войти"/></h3>
    <input type="submit">
</form>

<form style="text-align: center" action="${pageContext.request.contextPath}/start.jsp">
    <h3><c:out value="Зарегистрироваться"/></h3>
    <input type="submit">
</form>

