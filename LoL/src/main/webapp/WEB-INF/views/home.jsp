<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="login.do">
	<h1> Test</h1>
	<input type="text" name="id" ><br>
	<input type="password" name="passwd" ><br>
	<input type="submit" value="ok">
	<input type="reset" value="cancle"> 
</form>
${key}

</body>
</html>
