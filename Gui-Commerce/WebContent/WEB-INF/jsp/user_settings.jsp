<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gui-Commerce: Settings</title>
</head>
<body>
	<img src="logo.jpg">
	<br>
	User Settings
	<br>
	<br>Logged in as: <%= session.getAttribute("userName")%>
	<form action="logout" method="get">
		<input type="submit" value="Logout" id="logout">
	</form>
	
	
	<br>
	<br>
	<form action="addProduct" method="post">
		Product Name: 
		<br><input type="text" name="product" maxlength="20">
		<br>
		<br>Price:
		<br><input type="number" name="price" min="0" max="999999999">
		<br>
		<br>Link:
		<br>
		<br><input type="url" name="url"><br>
		<br>
		<br><input type="submit" value="Add New Product" id="add">
	</form>
	<br>
	<br>
	<form action="home" method="get">
		<input type="submit" value="Home" id="home">
	</form>
	<br>
	<br>
	<br>Your published products:
	<br>
	<table border="1" id="table">
	<tr>
	<td>Product ID</td>
	<td>User ID</td>
	<td>Product</td>
	<td>Price</td>
	<td></td>
	<td></td>
	</tr>
	<%@ page import ="java.util.ArrayList"%>
	<%@ page import ="java.util.HashMap"%>
	<%
	ArrayList<HashMap<String, String>> products = (ArrayList<HashMap<String, String>>) request.getAttribute("products");
	if(products != null){
		
	
		for (HashMap<String, String> product : products)
		{
		%>
		
		<tr>
		<td> <%=product.get("pid")%></td>
		<td> <%=product.get("user_id")%></td>
		<td> <%=product.get("prod")%></td>
		<td>g$ <%=product.get("price")%></td>
		<td><a href=<%=product.get("url")%> target="blank">Link</a> </td>
		<td>
			<form action="edit" method="post">
				<input type="hidden" name="ACTION" value="settings" />
				<input type="hidden" name="product_id" value="<%=product.get("pid")%>" />
				<input type="hidden" name="product_name" value="<%=product.get("prod")%>" />
				<input type="hidden" name="product_price" value="<%=product.get("price")%>" />
				<input type="submit" value="Edit" id="edit">
			</form>
		</td>
		</tr>
		
		<%       
		}
	} else {
		System.out.println("Something went wrong on Home!");
	}
	%>
	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>To delete your user and its associated products, click the button below (BE CAREFULL, THERE IS NO COMMING BACK...):
	<br>
	<form action="deleteUser" method="post">
		<input type="submit" value="DELETE USER" id="delete">
	</form>
</body>
</html>