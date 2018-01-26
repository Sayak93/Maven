<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body bgcolor="pink">

		<h2>
			<b>Register here</b>
		</h2>
<form action="register.htm" method=POST>
	<table>
		<tr>
			<td>User Id :</td>
			<td><input placeholder="enter name" name="name" type=text id="name" autocomplete="off"></td>
		</tr>
		<tr>
			<td>password :</td>
			<td><input type="text" name="pass" placeholder="enter password" id="pass" autocomplete="off"></td>
			</tr>
			<tr>
				<td>email :</td>
				<td><input type=text name="em" placeholder="enter email" id="email" autocomplete="off"></td>
			</tr>
			<tr>
				<td>address : </td><td><textarea name= "adr" style="resize:none" placeholder="enter address" id="adr"></textarea></td>
			</tr>
				<tr>
					<td><input type=submit value='Submit' id="sub" name="submit"></td>
					<td><input type=button value='Reset' name="reset"></td>
				</tr>
			</table>
		</form>
</body>







</html>