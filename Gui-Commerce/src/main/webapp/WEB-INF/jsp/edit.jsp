<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gui-Commerce: Edit</title>
</head>
<body>
	Logged in as: <%= session.getAttribute("userName")%>
	<br>
	<br>
	Edit product:
	<br>
	<br>Product:
	<br>
	<br><%= request.getAttribute("product_name")%>
	<br>
	<br>Price:
	<br>
	<br><%= request.getAttribute("product_price")%>
	<br>
	<br>
	<form action="delete" method="post">
		<input type="hidden" name="product_id" value="<%= request.getAttribute("product_id")%>" />
		<input type="submit" value="DELETE PRODUCT" id="delete">
	</form>
	<br>
	<br>
	<form action="edit" method="post">
		Product Name: 
		<br><input type="text" name="product" maxlength="20">
		<br>
		<br>Price:
		<br><input type="number" name="price" min="0" max="999999999" step="0.01">
		<br>
		<br>Link:
		<br>
		<br><input type="url" name="url"><br>
		<br>
		<br><input type="submit" value="Update Product" id="update">
		<input type="hidden" name="product_id" value="<%= request.getAttribute("product_id")%>" />
		<input type="hidden" name="ACTION" value="edit" />
	</form>
	<br>
	<br>
	<form action="settings" method="get">
		<input type="submit" value="Cancel" id="cancel">
	</form>
	
</body>
</html>