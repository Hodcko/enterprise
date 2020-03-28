
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Salary</title>
</head>
<body style="background-color: powderblue">
<h3 style="text-align: center"><c:out value="Введите общанную зарплату, уровень налогов и посомтрите сколько вам будут платить по факту"/></h3>

<form action="${pageContext.request.contextPath}/salary" method="post">
    <fieldset>
        <legend><c:out value="Введите данные для рассчета"/></legend>
    <label for="salary">Обещанный уровень зарплаты</label>
    <input id="salary" type="text" name="salary"><br/>


    <label for="fszn">Процент отчислений в ФСЗН</label>
    <input id="fszn" type="text" name="fszn"><br/>

    <label for="insurance">Страхование</label>
    <input id="insurance" type="text" name="insurance"><br/>

    <label for="incomeTax">Подоходный налог</label>
    <input id="incomeTax" type="text" name="incomeTax"><br/>

    <label for="unoinDeductions">Профсоюз</label>
    <input id="unoinDeductions" type="text" name="unoinDeductions"><br/>

    <input type="submit" value="Рассчитать">
    </fieldset>
</form>

</body>
</html>
