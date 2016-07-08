<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Page Find</title>
</head>
<body>
    <h1>Find City</h1>
        <form:form action="/testtask/respage" method="get">
            <c:if test="${not empty findCities}">
                Hello. Enter city name: <input type="text" name="cityname" />
                <button type="submit" name="search">Search</button>
            </c:if>
        </form:form>
        <form:form action="/testtask/mainpage" method="get">
            <c:if test="${not empty findCities}">
                If you need, you may  <button type="submit" name="update" value="update">Update All List Towns</button>
            </c:if>
        </form:form>
    <p></p>
    ${updMessage}
</body>
</html>

