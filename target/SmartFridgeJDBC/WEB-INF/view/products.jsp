<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

</head>

<body>
<table>
    <tr> <th>Name</th>
        <th>Measure</th>
        <th>Cost</th>
    </tr>

    <tr>
        <c:forEach var="productTables" items="${product}">
        <td>${productTables.name}</td>
        <td>${productTables.measure}</td>
        <td>${productTables.cost}</td>
    </tr>
    </c:forEach>

</table>

</body>
</html>