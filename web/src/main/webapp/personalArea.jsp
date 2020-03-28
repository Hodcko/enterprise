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

<c:if test="${manager == null}">

<h1 style="text-align: center"><c:out value="Вы вошли в личный кабинет"/></h1>
<hr>
<c:if test="${student.spec == null}">
<h2 style="text-align: center"><c:out value="Какой язык программирования будем изучать ?"/></h2>

<form style="text-align: center" action="${pageContext.request.contextPath}/spec" method="post">
    <h3><label style="text-align: center" for="lang">Java  PHP  <del>Assembler</del></label></h3>
    <br><input id="lang" type="text" name="lang"><br/>
    <br><input style="text-align: center" type="submit" value="Выбрать">
</form>
</c:if>
    <c:if test="${student.spec != null}">
<c:if test="${student.spec == 'java'}">
    <h2 style="text-align: center">Вы изучаете Java</h2>
    <h3 style="text-align: center">Отличный выбор</h3>
    <hr>
</c:if>
<c:if test="${student.spec == 'php'}">
    <h2 style="text-align: center">Вы изучаете PHP</h2>
    <hr>
</c:if>
<c:if test="${student.spec == 'assembler'}">
    <h2 style="text-align: center">Вы изучаете Assembler</h2>
    <hr>
</c:if>
        <form style="text-align: center" action="${pageContext.request.contextPath}/study">
            <h3><c:out value="Просмотреть план занятий"/></h3>
            <input type="submit" value="Просмотреть">
        </form>
    </c:if>
</c:if>


<c:if test="${manager != null}">
    <h2 style="text-align: center"><c:out value="Список всех студентов"/></h2>
    <table style="width: 100%">
                <tr>
                    <td><c:out value="Имя"/></td>
                    <td><c:out value="Фамилия"/></td>
                    <td><c:out value="Специальность"/></td>
                    <td><c:out value="ID"/></td>
                </tr>
    <c:forEach items="${students}" var="students">
            <tr>
                <td>${students.name}</td>
                <td>${students.secondName}</td>
                <td>${students.spec}</td>
                <td>${students.id}</td>
            </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>


