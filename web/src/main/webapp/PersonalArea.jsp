<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<head>
    <style>
        table, th, td{
            border: 1px solid black;
        }
    </style>
    <title>Personal area</title>
</head>
<body style="background-color: powderblue">
<h1 style="text-align: center"><c:out value="Вы вошли в личный кабинет"/></h1>
<c:if test="${teacher != null}">
    <c:if test="${curs == 'null'}">
    <h3 style="text-align: center"><c:out value="Объявить набор на курс"/></h3>

    <form style="text-align: center" action="${pageContext.request.contextPath}/curs" method="post">
        <fieldset>
            <legend><c:out value="Регистрационные данные"/></legend>
            <br><label for="name">Название курса</label>
            <br><input id="name" type="text" name="name"><br/>
            <br><label for="startDate">Дата начала курса</label>
            <br><input id="startDate" type="date" name="startDate"><br/>
            <br><label for="endDate">Дата окончания курса</label>
            <br><input id="endDate" type="date" name="endDate"><br/>
            <br><input type="submit" value="Объявить">
        </fieldset>
    </form>
    </c:if>
    <c:if test="${curs != 'null'}">
            <h2 style="text-align: center"><c:out value="Мой курс"/></h2>
        <table style="width: 100%">
            <tr>
                <td><c:out value="Название курса"/></td>
                <td><c:out value="${curs.name}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата начала курса"/></td>
                <td><c:out value="${curs.start}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата окончания курса"/></td>
                <td><c:out value="${curs.end}"/></td>
            </tr>
        </table>
            <h2 style="text-align: center"><c:out value="Список моих студентов"/></h2>
            <table style="width: 100%">
                        <tr>
                            <td><c:out value="Имя"/></td>
                            <td><c:out value="Фамилия"/></td>
                            <td><c:out value="Адрес электронной почты"/></td>
                            <td><c:out value="Итоговый балл"/></td>
                        </tr>
            <c:forEach items="${students}" var="students">
                    <tr>
                        <td>${students.name}</td>
                        <td>${students.secondName}</td>
                        <td>${students.email}</td>
                        <td>${students.grade}</td>
                    </tr>
            </c:forEach>
            </table>

    </c:if>
    <form style="text-align: center" action="${pageContext.request.contextPath}/change" method="post">
        <fieldset>
            <br><label  for="newPasswords">Изменить пароль</label>
            <br><input  id="newPasswords" type="text" name="newPasswords"><br/>
            <br><input  type="submit" value="Изменить">
        </fieldset>
    </form>
    <form style="text-align: center" action="${pageContext.request.contextPath}/logout" method="get">
        <fieldset>
            <legend><c:out value="Выйти из личного кабинета"/></legend>
            <br><input type="submit" name="logout" value="logout">
        </fieldset>
    </form>
    <form style="text-align: center" action="${pageContext.request.contextPath}/escape" method="get">
        <fieldset>
            <legend><c:out value="Закончить преподавание"/></legend>
            <br><input type="submit" name="escape" value="escape">
        </fieldset>
    </form>
</c:if>
<c:if test="${student != null}">
    <h3 style="text-align: center"><c:out value="Доступные курсы"/></h3>
    <c:if test="${curs != null}">
    <table style="width: 100%">
    <tr>
    <td><c:out value="Название курса"/></td>
    <td><c:out value="${curs.name}"/></td>
    </tr>
    <tr>
    <td><c:out value="Дата начала курса"/></td>
    <td><c:out value="${curs.start}"/></td>
    </tr>
    <tr>
    <td><c:out value="Дата окончания курса"/></td>
    <td><c:out value="${curs.end}"/></td>
    </tr>
    </table>
        <form style="text-align: center" action="${pageContext.request.contextPath}/study" method="post">
            <fieldset>
                <legend><c:out value="Записаться на курс"/></legend>
                <br><input type="submit" name="reg" value="Зарегистрироваться">
            </fieldset>
        </form>
    </c:if>
    <form style="text-align: center" action="${pageContext.request.contextPath}/change" method="post">
        <fieldset>
            <br><label  for="newPassword">Изменить пароль</label>
            <br><input  id="newPassword" type="text" name="newPassword"><br/>
            <br><input  type="submit" value="Изменить">
        </fieldset>
    </form>
    <form style="text-align: center" action="${pageContext.request.contextPath}/logout" method="get">
        <fieldset>
            <legend><c:out value="Выйти из личного кабинета"/></legend>
            <br><input type="submit" name="logout" value="logout">
        </fieldset>
    </form>
    <form style="text-align: center" action="${pageContext.request.contextPath}/escape" method="get">
        <fieldset>
            <legend><c:out value="Закончить обучение"/></legend>
            <br><input type="submit" name="escape" value="escape">
        </fieldset>
    </form>
</c:if>
<hr>
<form action="${pageContext.request.contextPath}/StartPage.jsp">
    <h3><c:out value="Перейти на главную страницу"/></h3>
    <input type="submit" value="Перейти">
</form>

    </body>
    </html>



