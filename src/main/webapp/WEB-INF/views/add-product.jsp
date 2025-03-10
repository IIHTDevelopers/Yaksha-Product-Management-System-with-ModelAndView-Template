<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
</head>
<body>
    <h1>Add Product</h1>
    <form:form action="/product/add" modelAttribute="product" method="POST">
        <label for="name">Product Name:</label>
        <form:input type="text" path="name" id="name" required="true"/><br><br>

        <label for="description">Description:</label>
        <form:textarea path="description" id="description" required="true"></form:textarea><br><br>

        <label for="price">Price:</label>
        <form:input type="number" path="price" id="price" required="true"/><br><br>

        <button type="submit">Add Product</button>
    </form:form>
    <br><a href="/products">Back to Product List</a>
</body>
</html>
