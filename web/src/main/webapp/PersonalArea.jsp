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
    <c:if test="${curs.end == null}">
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
    <c:if test="${curs.end != null}">
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
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Адрес электронной почты</th>
                    <th>Итоговый балл</th>
                </tr>

                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.secondName}</td>
                        <td>${student.email}</td>
                        <td>${student.grade}</td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${currentPage != 1}">
            <td><a href="${pageContext.request.contextPath}/pagination?page=${currentPage - 1}">Previous</a></td>
            </c:if>


            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="${pageContext.request.contextPath}/pagination?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <c:if test="${currentPage lt noOfPages}">
            <td><a href="${pageContext.request.contextPath}/pagination?page=${currentPage + 1}">Next</a></td>
            </c:if>

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
    <c:if test="${studentOnCurs == false}">

    <c:if test="${javaCurs.end != null && studentOnCurs == false}">
        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по Java"/></h3>
        <table style="width: 100%">
    <tr>
    <td><c:out value="Название курса"/></td>
    <td><c:out value="${javaCurs.name}"/></td>
    </tr>
    <tr>
    <td><c:out value="Дата начала курса"/></td>
    <td><c:out value="${javaCurs.start}"/></td>
    </tr>
    <tr>
    <td><c:out value="Дата окончания курса"/></td>
    <td><c:out value="${javaCurs.end}"/></td>
    </tr>
    </table>


    </c:if>
    <c:if test="${phpCurs.end != null && studentOnCurs == false}">
        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по PHP"/></h3>
        <table style="width: 100%">
            <tr>
                <td><c:out value="Название курса"/></td>
                <td><c:out value="${phpCurs.name}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата начала курса"/></td>
                <td><c:out value="${phpCurs.start}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата окончания курса"/></td>
                <td><c:out value="${phpCurs.end}"/></td>
            </tr>
        </table>


    </c:if>
    <c:if test="${cCurs.end != null && studentOnCurs == false}">
        <h3 style="text-align: center"><c:out value="Доступные для записи курсы по C++"/></h3>
        <table style="width: 100%">
            <tr>
                <td><c:out value="Название курса"/></td>
                <td><c:out value="${cCurs.name}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата начала курса"/></td>
                <td><c:out value="${cCurs.start}"/></td>
            </tr>
            <tr>
                <td><c:out value="Дата окончания курса"/></td>
                <td><c:out value="${cCurs.end}"/></td>
            </tr>
        </table>


    </c:if>
        <form style="text-align: center" action="${pageContext.request.contextPath}/study" method="post">
            <fieldset>
                <h2 style="text-align: center"><c:out value="Выберите курсы которые будете посещать"/></h2>
                <label>
                    <input type="checkbox" name="javaReg" value="java">
                </label> Java
                <label>
                    <input type="checkbox" name="phpReg" value="php">
                </label> PHP
                <label>
                    <input type="checkbox" name="cReg" value="c++">
                </label> C++
                <br><input type="submit" name="userType" value="Записаться">
            </fieldset>
        </form>
    </c:if>



    <c:if test="${studentOnCurs == true}">

        <c:if test="${classmatesJava != null}">

        <h2 style="text-align: center"><c:out value="Список студентов курса по Java"/></h2>
    <table style="width: 100%">
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Адрес электронной почты</th>
            </tr>

            <c:forEach var="classmate" items="${classmatesJava}">
                <tr>
                    <td>${classmate.name}</td>
                    <td>${classmate.secondName}</td>
                    <td>${classmate.email}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
<hr>

        <c:if test="${classmatesPHP != null}">

            <h2 style="text-align: center"><c:out value="Список студентов курса по PHP"/></h2>
            <table style="width: 100%">
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Адрес электронной почты</th>
                </tr>

                <c:forEach var="classmate" items="${classmatesPHP}">
                    <tr>
                        <td>${classmate.name}</td>
                        <td>${classmate.secondName}</td>
                        <td>${classmate.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
<hr>
        <c:if test="${classmatesC != null}">

            <h2 style="text-align: center"><c:out value="Список студентов курса по C++"/></h2>
            <table style="width: 100%">
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Адрес электронной почты</th>
                </tr>

                <c:forEach var="classmate" items="${classmatesC}">
                    <tr>
                        <td>${classmate.name}</td>
                        <td>${classmate.secondName}</td>
                        <td>${classmate.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:if>
<hr>
    <form style="text-align: center" action="${pageContext.request.contextPath}/change" method="post">
        <fieldset>
            <br><label  for="newPassword">Изменить пароль</label>
            <br><input  id="newPassword" type="text" name="newPassword"><br/>
            <br><input  type="submit" value="Изменить">
        </fieldset>
    </form>
    <form style="text-align: center" action="${pageContext.request.contextPath}/StudyPage.jsp">
        <fieldset>
            <legend><c:out value="Посмотреть план занятий"/></legend>
            <br><input type="submit" name="logout" value="Посмотреть">
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



