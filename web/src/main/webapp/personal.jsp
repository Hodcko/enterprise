<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3 style="text-align: center"><c:out value="Вход в личный кабинет"/></h3>
    <form action="${pageContext.request.contextPath}/personal" method="post">
       <br><label  for="login">Логин</label>
        <input  id="login" type="text" name="login"><br/>
        <br><label  for="password">Пароль</label>
        <input  id="password" type="text" name="password"><br/>
        <br><input  type="submit">
    </form>


