<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>First Jsp</title>

</head>
<body style="background-color: powderblue">

    <h1 style="text-align: center"><c:out value="Главная страница"/></h1>


</body>
</html>

<form action="${pageContext.request.contextPath}/personal.jsp">
    <h3><c:out value="Войти"/></h3>
        <input type="submit" value="Войти">
    </form>
<hr>
<form action="${pageContext.request.contextPath}/registration.jsp">
        <h3><c:out value="Зарегистрироваться"/></h3>
        <input  type="submit" value="Зарегистрироваться" >
    </form>
<hr>
<form action="${pageContext.request.contextPath}/study">
    <h3><c:out value="Просмотреть план занятий"/></h3>
    <input type="submit" value="Просмотреть">
</form>
<hr>
<form action="${pageContext.request.contextPath}/salary.jsp">
    <h3><c:out value="Рассчитать зарплату"/></h3>
    <input type="submit" value="Рассчитать">
</form>

