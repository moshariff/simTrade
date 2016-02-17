<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function gohome() {

		window.location = "http://localhost:8080/home"
	}
</script>
</head>
<body>
	<h3>Successfully re-activated</h3>
	<form>
		<input type="button" onClick="gohome()" value="Take Me Home!">
	</form>
</body>
</html>