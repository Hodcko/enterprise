<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>



<%--<html>--%>
<%--<head>--%>
<%--    <style>--%>
<%--        table, th, td{--%>
<%--            border: 1px solid black;--%>
<%--        }--%>
<%--    </style>--%>
<%--    <title>Personal area</title>--%>
<%--</head>--%>
<%--<body style="background-color: powderblue">--%>

<%--<c:if test="${student != null}">--%>
<%--    <c:if test="${studentOnCurs == false}">--%>

<%--    <c:if test="${javaCurs.end != null && studentOnCurs == false}">--%>
<%--        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по Java"/></h3>--%>
<%--        <table style="width: 100%">--%>
<%--    <tr>--%>
<%--    <td><c:out value="Название курса"/></td>--%>
<%--    <td><c:out value="${javaCurs.name}"/></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--    <td><c:out value="Дата начала курса"/></td>--%>
<%--    <td><c:out value="${javaCurs.start}"/></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--    <td><c:out value="Дата окончания курса"/></td>--%>
<%--    <td><c:out value="${javaCurs.end}"/></td>--%>
<%--    </tr>--%>
<%--    </table>--%>


<%--    </c:if>--%>
<%--    <c:if test="${phpCurs.end != null && studentOnCurs == false}">--%>
<%--        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по PHP"/></h3>--%>
<%--        <table style="width: 100%">--%>
<%--            <tr>--%>
<%--                <td><c:out value="Название курса"/></td>--%>
<%--                <td><c:out value="${phpCurs.name}"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><c:out value="Дата начала курса"/></td>--%>
<%--                <td><c:out value="${phpCurs.start}"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><c:out value="Дата окончания курса"/></td>--%>
<%--                <td><c:out value="${phpCurs.end}"/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>


<%--    </c:if>--%>
<%--    <c:if test="${cCurs.end != null && studentOnCurs == false}">--%>
<%--        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по C++"/></h3>--%>
<%--        <table style="width: 100%">--%>
<%--            <tr>--%>
<%--                <td><c:out value="Название курса"/></td>--%>
<%--                <td><c:out value="${cCurs.name}"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><c:out value="Дата начала курса"/></td>--%>
<%--                <td><c:out value="${cCurs.start}"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><c:out value="Дата окончания курса"/></td>--%>
<%--                <td><c:out value="${cCurs.end}"/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>


<%--    </c:if>--%>
<%--        <form style="text-align: center" action="${pageContext.request.contextPath}/study" method="post">--%>
<%--            <fieldset>--%>
<%--                <h2 style="text-align: center"><c:out value="Выберите курсы которые будете посещать"/></h2>--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="javaReg" value="java">--%>
<%--                </label> Java--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="phpReg" value="php">--%>
<%--                </label> PHP--%>
<%--                <label>--%>
<%--                    <input type="checkbox" name="cReg" value="c++">--%>
<%--                </label> C++--%>
<%--                <br><input type="submit" name="userType" value="Записаться">--%>
<%--            </fieldset>--%>
<%--        </form>--%>
<%--    </c:if>--%>



<%--    <c:if test="${studentOnCurs == true}">--%>

<%--        <c:if test="${classmatesJava != null}">--%>

<%--        <h2 style="text-align: center"><c:out value="Список студентов курса по Java"/></h2>--%>
<%--    <table style="width: 100%">--%>
<%--            <tr>--%>
<%--                <th>Имя</th>--%>
<%--                <th>Фамилия</th>--%>
<%--                <th>Адрес электронной почты</th>--%>
<%--            </tr>--%>

<%--            <c:forEach var="classmate" items="${classmatesJava}">--%>
<%--                <tr>--%>
<%--                    <td>${classmate.name}</td>--%>
<%--                    <td>${classmate.secondName}</td>--%>
<%--                    <td>${classmate.email}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--        </c:if>--%>
<%--<hr>--%>

<%--        <c:if test="${classmatesPHP != null}">--%>

<%--            <h2 style="text-align: center"><c:out value="Список студентов курса по PHP"/></h2>--%>
<%--            <table style="width: 100%">--%>
<%--                <tr>--%>
<%--                    <th>Имя</th>--%>
<%--                    <th>Фамилия</th>--%>
<%--                    <th>Адрес электронной почты</th>--%>
<%--                </tr>--%>

<%--                <c:forEach var="classmate" items="${classmatesPHP}">--%>
<%--                    <tr>--%>
<%--                        <td>${classmate.name}</td>--%>
<%--                        <td>${classmate.secondName}</td>--%>
<%--                        <td>${classmate.email}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </c:if>--%>
<%--<hr>--%>
<%--        <c:if test="${classmatesC != null}">--%>

<%--            <h2 style="text-align: center"><c:out value="Список студентов курса по C++"/></h2>--%>
<%--            <table style="width: 100%">--%>
<%--                <tr>--%>
<%--                    <th>Имя</th>--%>
<%--                    <th>Фамилия</th>--%>
<%--                    <th>Адрес электронной почты</th>--%>
<%--                </tr>--%>

<%--                <c:forEach var="classmate" items="${classmatesC}">--%>
<%--                    <tr>--%>
<%--                        <td>${classmate.name}</td>--%>
<%--                        <td>${classmate.secondName}</td>--%>
<%--                        <td>${classmate.email}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </c:if>--%>
<%--    </c:if>--%>
<%--<hr>--%>
<%--    <form style="text-align: center" action="${pageContext.request.contextPath}/change" method="post">--%>
<%--        <fieldset>--%>
<%--            <br><label  for="newPassword">Изменить пароль</label>--%>
<%--            <br><input  id="newPassword" type="text" name="newPassword"><br/>--%>
<%--            <br><input  type="submit" value="Изменить">--%>
<%--        </fieldset>--%>
<%--    </form>--%>
<%--    <form style="text-align: center" action="${pageContext.request.contextPath}/StudyPage.jsp">--%>
<%--        <fieldset>--%>
<%--            <legend><c:out value="Посмотреть план занятий"/></legend>--%>
<%--            <br><input type="submit" name="logout" value="Посмотреть">--%>
<%--        </fieldset>--%>
<%--    </form>--%>
<%--    <form style="text-align: center" action="${pageContext.request.contextPath}/logout" method="get">--%>
<%--        <fieldset>--%>
<%--            <legend><c:out value="Выйти из личного кабинета"/></legend>--%>
<%--            <br><input type="submit" name="logout" value="logout">--%>
<%--        </fieldset>--%>
<%--    </form>--%>
<%--    <form style="text-align: center" action="${pageContext.request.contextPath}/escape" method="get">--%>
<%--        <fieldset>--%>
<%--            <legend><c:out value="Закончить обучение"/></legend>--%>
<%--            <br><input type="submit" name="escape" value="escape">--%>
<%--        </fieldset>--%>
<%--    </form>--%>
<%--</c:if>--%>
<%--<hr>--%>
<%--<form action="${pageContext.request.contextPath}/StartPage.jsp">--%>
<%--    <h3><c:out value="Перейти на главную страницу"/></h3>--%>
<%--    <input type="submit" value="Перейти">--%>
<%--</form>--%>

<%--    </body>--%>
<%--    </html>--%>



