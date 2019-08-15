<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gui-Commerce: Home</title>
</head>
<body>
	<img src="logo.jpg">
	<br>
	The number 1 E-Commerce without a good GUI (Graphical User Interface)
	<br> 
	<br> Please login to announce your product:
	<br>
	<br>

	<form action="login" method="get">
		<input type="submit" value="Login" id="login">
	</form>
	<br>
	<br>Or register if you don´t have an account:
	<br>
	<br>
	<form action="register" method="get">
		<input type="submit" value="Register" id="register">
	</form>
	<br>
	<br>
	Current offers:
	<table border="1" id="table">
	
	<tr>
	<td> <%="ID"%></td>
	<td> <%="User Name"%></td>
	<td><%="Product"%> </td>
	<td> <%="Price"%></td>
	<td> <%="Link"%></td>
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
		<td> <%=product.get("user_name")%></td>
		<td> <%=product.get("prod")%></td>
		<td>g$ <%=product.get("price")%></td>
		<td><a href=<%=product.get("url")%> target="blank">Link</a> </td>
		
		</tr>
		
		<%       
		}
	} else {
		System.out.println("Something went wrong on Home!");
	}
	%>
	
	</table>



</body>
</html>

