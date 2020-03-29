<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>Personal area</title>
</head>
<body style="background-color: powderblue">
<h2 style="text-align: center"><c:out value="Регистрация прошла успешно"/></h2>
<p style="align-content: center">
<c:if test="${student != null}">
<form style="text-align: center">
    <fieldset>
        <legend><c:out value="Ваши данные для входа в личный кабинет"/></legend>
        <h3><c:out value="Ваш логин"/></h3>
        <h2><c:out value="${student.name}"/></h2>
        <br><h3><c:out value="Ваш пароль"/></h3>
        <button type="button" onclick="alert(${student.id})"><h3><c:out value="Показать пароль"/></h3></button>
    </fieldset>
</form>

</c:if>

<form style="text-align: center" action="${pageContext.request.contextPath}/personal.jsp">
    <h3><c:out value="Войти в личный кабинет"/></h3>
        <input type="submit" value="Войти">
    </form>
</body>

