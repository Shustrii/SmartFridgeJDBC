<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

</head>

<body>
<div style="width: 300px; float: left; display: block; border: 1px solid blue; margin: 2px">
<table>
    <tr> <th>Name</th>
        <th>Quantity</th>
    </tr>

    <tr>
        <c:forEach var="fridgeTables" items="${fridge}">
            <c:url var="deleteButton" value="/deleteFromFridge">
                <c:param name="id" value="${fridgeTables.id}"/>
            </c:url>
        <td>${fridgeTables.productName}</td>
            <td>${fridgeTables.quantity}</td>
            <td>
                <input type="button" value="Delete"
                       onclick="window.location.href='${deleteButton}'">
            </td>
    </tr>

        </c:forEach>

</table>
</div>
<div style="width: 300px; float: left; display: block; border: 1px solid gold; margin: 2px">
    <form:form method="post" modelAttribute="productInFridge" action="addProductFridge">
        <form:select path="productID" size="1">
            <form:options items="${productNotInFridge}" itemLabel="name" itemValue="id"/>
        </form:select>
        <form:input path="quantity"/>
        <input type="submit" />
    </form:form>
</div>
</body>
</html>