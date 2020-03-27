<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3 style="text-align: center"><c:out value="Регистрация студента"/></h3>
    <form action="${pageContext.request.contextPath}/student" method="post">
        <br><label for="name">Имя</label>
        <input id="name" type="text" name="name"><br/>
        <br><label for="secondName">Фамилия</label>
        <input id="secondName" type="text" name="secondName"><br/>

        <br><input type="submit" value="Зарегистрироваться">

    </form>





