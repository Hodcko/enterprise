
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body style="background-color: powderblue">

<c:if test="${student.curs_id == '1'}">
 <h2><c:out value="План занятий"/></h2>
 <h3><c:out value="1. Почитай что-нибудь"/></h3>
 <a href="https://www.google.com/search?q=%D0%BA%D0%BD%D0%B8%D0%B3%D0%B8+%D0%BF%D0%BE+Java&rlz=1C5CHFA_enBY852BY852&oq=
 %D0%BA%D0%BD%D0%B8%D0%B3%D0%B8+%D0%BF%D0%BE+Java&aqs=chrome..69i57j0l7.5680j0j7&sourceid=chrome&ie=UTF-8"> Почитать</a>
 <h3><c:out value="2. Посмотри что-нибудь"/></h3>
 <a href="https://www.youtube.com/results?search_query=Java+%D1%83%D1%80%D0%BE%D0%BA%D0%B8"> Посмотреть</a>
 <h3><c:out value="3. Попиши код"/></h3>
 <a href="https://www.jetbrains.com/ru-ru/idea/download/#section=mac">Писать код</a>
 <h3><c:out value="4. While(true) goto п.1"/></h3>
</c:if>

 <c:if test="${student.curs_id == '2'}">
  <h2><c:out value="План занятий"/></h2>
  <h3><c:out value="1. Почитай что-нибудь"/></h3>
  <a href="https://www.google.com/search?rlz=1C5CHFA_enBY852BY852&ei=kbKAXseFLO-hrgSpvYF4&q=%D0%BA%D0%BD%D0%B8%D0%B3%D0%B
  8+%D0%BF%D0%BE+PHP&oq=%D0%BA%D0%BD%D0%B8%D0%B3%D0%B8+%D0%BF%D0%BE+PHP&gs_lcp=CgZwc3ktYWIQAzICCAAyAggAMgIIADICCAAyAggAMg
  IIADICCAAyAggAOgQIABBHOgQIABBDOgQIABAKUOG5AViuxwFgjswBaABwAngAgAGXAogB6waSAQU1LjEuMZgBAKABAaoBB2d3cy13aXo&sclient=psy-
  ab&ved=0ahUKEwiHkoCm9L_oAhXvkIsKHaleAA8Q4dUDCAs&uact=5"> Почитать</a>
  <h3><c:out value="2. Посмотри что-нибудь"/></h3>
  <a href="https://www.youtube.com/results?search_query=%D1%83%D1%80%D0%BE%D0%BA%D0%B8+php"> Посмотреть</a>
  <h3><c:out value="3. Попиши код"/></h3>
  <a href="https://www.jetbrains.com/phpstorm/promo/?gclid=EAIaIQobChMI7cT0jPW_6AIVw0QYCh0qWAwHEAAYASAAEgKvrPD_BwE">Писать код</a>
  <h3><c:out value="4. While(true) goto п.1"/></h3>
 </c:if>

<c:if test="${student.curs_id == '3'}">
    <h2><c:out value="План занятий"/></h2>
 <h3><c:out value="1. Почитай что-нибудь"/></h3>
 <a href="https://www.google.com/search?q=%D0%BA%D0%BD%D0%B8%D0%B3%D0%B8+%D0%BF%D0%BE+assembler&rlz=1C5CHFA_enBY852BY852&
 oq=%D0%BA%D0%BD%D0%B8%D0%B3%D0%B8+%D0%BF%D0%BE+asse&aqs=chrome.1.69i57j0l4.5304j0j4&sourceid=chrome&ie=UTF-8"> Почитать</a>
 <h3><c:out value="2. Посмотри что-нибудь"/></h3>
 <a href="https://www.youtube.com/results?search_query=%D1%83%D1%80%D0%BE%D0%BA%D0%B8+assembler"> Посмотреть</a>
 <h3><c:out value="3. Попиши код"/></h3>
 <a href="https://flatassembler.net/">Писать код</a>
 <h3><c:out value="4. While(true) goto п.1"/></h3>
</c:if>
<form style="text-align: center" action="${pageContext.request.contextPath}/Test.jsp" method="post">
 <fieldset>
  <legend><c:out value="Пройти тест"/></legend>
  <br><input type="submit" name="test" value="Test">
 </fieldset>
</form>

  </body>
</html>

