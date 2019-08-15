<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gui-Commerce: Login</title>
</head>
<body>
	<img src="logo.jpg">
	<br>
	Enter your Gui-Commerce Username and Password below:
	<br>
	<br>

	<form action="login" method="post">
		Username:
		<br><input type="text" name="username" maxlength="20">
		<br>
		<br>Password:
		<br><input type="password" name="password" maxlength="20">
		<br>
		<br><input type="submit" value="Login" id="login">
	</form>
	<br>
	<br>
	<form action="home" method="get">
		<input type="submit" value="Home" id="home">
	</form>
	</body>

</body>
</html>