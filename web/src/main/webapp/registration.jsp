<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body style="background-color: powderblue">
<h2 style="text-align: center"><c:out value="Регистрация студента"/></h2>

    <form style="text-align: center" action="${pageContext.request.contextPath}/student" method="post">
        <fieldset>
            <legend><c:out value="Регистрационные данные"/></legend>
        <br><label for="name">Имя</label>
        <br><input id="name" type="text" name="name"><br/>
        <br><label for="secondName">Фамилия</label>
        <br><input id="secondName" type="text" name="secondName"><br/>
        <br><input type="submit" value="Зарегистрироваться">
        </fieldset>
    </form>

</body>
</html>





