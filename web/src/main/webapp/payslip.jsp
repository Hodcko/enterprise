
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <style>
        table, th, td{
            border: 1px solid black;
        }
    </style>
    <title>Payslip</title>
</head>
<body style="background-color: powderblue">

<h1 style="text-align: center"><c:out value="Рассчетная ведомость"/></h1>

<table style="width: 100%">
    <tr>
        <td><c:out value="Отчисления в ФСЗН"/></td>
        <td><c:out value="${salary.fszn}"/></td>
    </tr>
    <tr>
        <td><c:out value="Страхование"/></td>
        <td><c:out value="${salary.insurance}"/></td>
    </tr>
    <tr>
        <td><c:out value="Подоходный налог"/></td>
        <td><c:out value="${salary.incomeTax}"/></td>
    </tr>
    <tr>
        <td><c:out value="Профсоюз"/></td>
        <td><c:out value="${salary.unoinDeductions}"/></td>
    </tr>
    <tr>
        <td><c:out value="Итого на руки"/></td>
        <td><c:out value="${salary.amountToBePaid}"/></td>
    </tr>
</table>

</body>
</html>

<form action="${pageContext.request.contextPath}/start">
    <h3><c:out value="Вернуться на главную страницу"/></h3>
    <input type="submit" value="Перейти">
</form>
