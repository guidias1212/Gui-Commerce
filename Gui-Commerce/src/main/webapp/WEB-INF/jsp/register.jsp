<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gui-Commerce: Register</title>
</head>
<body>
	<img src="logo.jpg">
	<br>
	Register new Gui-Commerce account
	<br>
	<br>

	<form action="register" method="post">
		Name:
		<br><input type="text" name="name" maxlength="20">
		<br>
		<br>Username:
		<br><input type="text" name="username" maxlength="20">
		<br>
		<br>Password:
		<br><input type="password" name="password_1" maxlength="20">
		<br>
		<br>Repeat Password:
		<br><input type="password" name="password_2" maxlength="20">
		<br>
		<br>
		<br><input type="submit" value="Create" id="create">
	</form>
	<br>
	<br>
	<form action="home" method="get">
		<input type="submit" value="Home" id="home">
	</form>
	</body>
</body>
</html>