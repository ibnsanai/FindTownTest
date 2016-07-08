<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Search</title>
    <style type="text/css">
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border: 1px #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            padding: 8px;
            border: 1px solid #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            padding: 8px;
            border: 1px solid #666666;
            background-color: #ffffff;
        }
    </style>

</head>
<body>
    <h1>Result Serch</h1>
    <form:form action="/testtask/mainpage" method="get">
        ${findMessage}<button type="submit" name="back">Back to Search</button>
        <p></p>
        <c:if test="${not empty findCities}">
            <table class="gridtable">
                <tr>
                    <th>City Name</th>
                    <th>Province</th>
                    <th>Providers</th>
                    <th>City ID</th>
                </tr>
                <c:forEach items="${findCities}" var="city">
                    <tr>
                        <td><c:out value="${city.cityName}" /></td>
                        <td><c:out value="${city.province}" /></td>
                        <td>
                            <c:forEach items="${city.providers}" var="provider">
                                <c:out value="${provider};" />
                            </c:forEach>
                        </td>
                        <td><c:out value="${city.cityId}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </form:form>
</body>
</html>
