<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
</head>
<body>
    <h1>Edit Product</h1>
    <form:form action="/product/edit/${product.id}" modelAttribute="product" method="POST">
        <input type="hidden" path="id"/>

        <label for="name">Product Name:</label>
        <form:input type="text" path="name" id="name" value="${product.name}" required="true"/><br><br>

        <label for="description">Description:</label>
        <form:textarea path="description" id="description" rows="4" cols="50"/><br><br>

        <label for="price">Price:</label>
        <form:input type="number" path="price" id="price" value="${product.price}" required="true"/><br><br>

        <button type="submit">Update Product</button>
    </form:form>
    <br><a href="/products">Back to Product List</a>
</body>
</html>
