<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Test</title>
</head>
<body style="background-color: powderblue">
<h2 style="text-align: center"><c:out value="Тест"/></h2>

<form style="text-align: center" action="${pageContext.request.contextPath}/test" method="post">
    <fieldset>
        <legend><c:out value="Вам необходиом ответить на все вопросы"/></legend>
        <label for="salary">What programming language are you learning ?</label>
        <input id="salary" type="text" name="first"><br/>

        <label for="fszn">Object oriented ...</label>
        <input id="fszn" type="text" name="second"><br/>

        <label for="insurance">What we are implementing?</label>
        <input id="insurance" type="text" name="third"><br/>

        <label for="incomeTax">How many access modifiers in Java?</label>
        <input id="incomeTax" type="text" name="fourth"><br/>

        <label for="unoinDeductions">What version of JDK is one love?</label>
        <input id="unoinDeductions" type="text" name="fifth"><br/>

        <input type="submit" value="Проверить">
    </fieldset>
</form>

</body>
</html>
