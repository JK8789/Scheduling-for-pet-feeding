<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
</head>
    <body>
    <table>
        <c:forEach items="${persons}" var="persons">
            <tr>
                <td>${person.id}</td>
                <td><c:out value="${persons.name}"/></td>
                <td><c:out value="${persons.email}"/></td>
                <td><c:out value="${persons.password}"/></td>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
