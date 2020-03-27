<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <style>
        table, th, td{
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td{
            padding: 5px;
            text-align: left;
        }
    </style>
    <title>Personal area</title>
</head>

<h2 style="text-align: center"><c:out value="Регистрация прошла успешно"/></h2>
<c:if test="${student != null}">

    <h3> <c:out value="Логин"/></h3>
    <h3> <c:out value="${student.name}"/></h3>

    <h3> <c:out value="Пароль"/></h3>
    <h3> <c:out value="${student.id}"/></h3>


</c:if>
<form action="${pageContext.request.contextPath}/personal.jsp">
    <h3><c:out value="Войти в личный кабинет"/></h3>
        <input type="submit" value="Войти">
    </form>


