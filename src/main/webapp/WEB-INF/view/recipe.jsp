<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

</head>

<body>
<div style="width: 300px; float: left; display: block; border: 1px solid red; margin: 2px">
    <table>
    <tr> <th>Name Of Recipe</th>
    </tr>

    <tr>
        <c:forEach var="recipeTables" items="${recipe}">

        <td><a href="/SmartFridgeJDBC_war/recipe/${recipeTables.id}">${recipeTables.recipe}</a></td>
    </tr>
    </c:forEach>
        <form:form method="post" modelAttribute="newRecipe" action="addNewRecipe">
        <form:input path="recipe"/>
        <input type="submit"/>
        </form:form>

</table>
</div>



<div style="width: 300px; float: left; display: block; border: 1px solid blue; margin: 2px">
    <table>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
        </tr>
        <tr>
        <c:forEach var="product" items="${productTables}">
        <c:url var="deleteButton" value="/delete">
            <c:param name="id" value="${product.id}"/>
            <c:param name="recipeId" value="${recipeId}"/>
            </c:url>
            <tr>
                <td>${product.name}</td>
                <td>${product.quantity}</td>
            </tr>
        <td>
            <input type="button" value="Delete"
            onclick="window.location.href='${deleteButton}'">
        </td>
        </tr>
        </c:forEach>

    </table>
</div>

<div style="width: 300px; float: left; display: block; border: 1px solid gold; margin: 2px">
    <form:form method="post" modelAttribute="product" action="../addRecipeProduct">
        <form:hidden path="recipeId" value="${recipeId}" />
        <form:select path="id" size="1">
            <form:options items="${productNotInRecipe}" itemLabel="name" itemValue="id"/>
        </form:select>
        <form:input path="quantity"/>
        <input type="submit" />
    </form:form>
</div>

</body>
</html>