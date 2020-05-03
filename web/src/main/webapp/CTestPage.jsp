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
        <label for="salary">What programming language does c++ inherit from ?</label>
        <input id="salary" type="text" name="first"><br/>

        <label for="fszn">What is the logical data type called in c++ ?</label>
        <input id="fszn" type="text" name="second"><br/>

        <label for="insurance">Does c++ support multiple inheritance?</label>
        <input id="insurance" type="text" name="third"><br/>

        <label for="incomeTax">C++ supports dynamic polymorphism and ... polymorphism</label>
        <input id="incomeTax" type="text" name="fourth"><br/>

        <label for="unoinDeductions">How many special functions does the class have in c++ by default?</label>
        <input id="unoinDeductions" type="text" name="fifth"><br/>

        <input type="submit" name="test" value="c++">
    </fieldset>
</form>

</body>
</html>