<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simcard</title>
</head>
<body>
	<h1>WELCOME TO SIM CARD APPLICATION</h1>
	<h2>
		<!-- p>Todays Date: <%=new java.util.Date()%></p -->
	</h2>
	<form action="" method="get">
		<h3 style="background-color: cyan;">Choose an action</h3>
		<TABLE>
			<tr>
				<td><a href="http://localhost:8080/user/book-a-sim"
					style="color: red; background-color: silver; font-family: fantasy; font-style: italic;"><h2>Book
							a Sim</h2></a></td>
			</tr>
			<tr>
				<td><a href="http://localhost:8080/sim-details/sim-form"
					style="color: red; background-color: silver; font-family: fantasy; font-style: italic;"><h2>Deposit
							a Sim</h2></a></td>
			</tr>
			<tr>
				<td><a href="http://localhost:8080/sim-details/return-a-sim"
					style="color: red; background-color: silver; font-family: fantasy; font-style: italic;"><h2>Return
							a Sim</h2></a></td>
			</tr>
		</TABLE>


	</form>

</body>
</html>