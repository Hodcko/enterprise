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
        <label for="salary">PHP is a ... typing programming language</label>
        <input id="salary" type="text" name="first"><br/>

        <label for="fszn">How many scalar data types in PHP?</label>
        <input id="fszn" type="text" name="second"><br/>

        <label for="insurance">What character is used to access variables?</label>
        <input id="insurance" type="text" name="third"><br/>

        <label for="incomeTax">Starting with which version of PHP has full support for object-oriented programming?</label>
        <input id="incomeTax" type="text" name="fourth"><br/>

        <label for="unoinDeductions">What is «Paamayim Nekudotayim» ?</label>
        <input id="unoinDeductions" type="text" name="fifth"><br/>

        <input type="submit" name="test" value="PHP">
    </fieldset>
</form>

</body>
</html>
