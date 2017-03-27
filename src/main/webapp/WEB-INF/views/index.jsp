<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>

    <c:url value="/resources/css/bootstrap.css" var="bootstrap"></c:url>
    <c:url value="/resources/js/angular.js" var="angular"></c:url>

    <link href='${bootstrap}' rel="stylesheet" />
    <script type="text/javascript" src="${angular}"></script>

</head>

<body ng-app>

<h1>${msg}</h1>
    <div class="container">

        {{ 20 + 10}}

        <div>
            {{10 + 10}}

            <h1 class="h1">Hello World</h1>

        </div>
    </div>
</body>
</html>
