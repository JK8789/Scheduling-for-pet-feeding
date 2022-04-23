<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link REL="stylesheet" TYPE="text/css" HREF="css/style.css">
</head>
    <body class="styleBody">
    <table>
        <c:forEach items="${persons}" var="persons">
            <tr>
                <td><c:out value="${persons.id}"/></td>
                <td><c:out value="${persons.name}"/></td>
                <td><c:out value="${persons.email}"/></td>
                <td><c:out value="${persons.password}"/></td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
