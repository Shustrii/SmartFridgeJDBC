<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

</head>

<body>
<div style="width: 300px; float: left; display: block; border: 1px solid gold; margin: 2px">
    <form:form method="post" modelAttribute="recipe" action="order">
        <form:select path="id" size="1">
            <form:options items="${allRecipe}" itemLabel="recipe" itemValue="id"/>
        </form:select>
        <input type="submit" />
    </form:form>
</div>
<div style="width: 700px; float: left; display: block; border: 1px solid blue; margin: 2px">
    <table>
        <tr>
            <td style="width: 70px; float: left; display: block; border: 1px solid red; margin: 2px">Name</td>
            <td style="width: 70px; float: left; display: block; border: 1px solid green; margin: 2px">Quantity</td>
            <td style="width: 100px; float: left; display: block; border: 1px solid gold; margin: 2px">Price for one</td>
            <td style="width: 100px; float: left; display: block; border: 1px solid orange; margin: 2px">Price for all</td>
        </tr>
    <c:forEach var="product" items="${orderProd}">

        <tr>
            <td style="width: 70px; float: left; display: block; border: 1px solid red; margin: 2px">
                    ${product.name}
            </td>
            <td style="width: 70px; float: left; display: block; border: 1px solid green; margin: 2px">
                    ${product.quantity}
            </td>
            <td style="width: 100px; float: left; display: block; border: 1px solid gold; margin: 2px">
                    ${product.cost}
            </td>
            <td style="width: 100px; float: left; display: block; border: 1px solid orange; margin: 2px">
                    ${product.price}
            </td>

        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>