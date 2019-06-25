<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Media Buddy</title>
</head>
<body style="font-family:Arial;font-size:20px;">
	<div style="height:65px;align: center;align-content:center;background: #2240B7;font-family: Arial;color: white;"">
		<br><b>
		<a href="" style="font-family:garamond;font-size:34px;text-align:center;display:block;color:white;text-decoration: none;">Media Buddy</a></b>		
			<div style="height:25px;background: #2240B7;font-family: Arial;color: white;">
		</div>	
	</div>
	<br><br>
	<div>
		<a href="<%=request.getContextPath()%>/login" style="font-size:34px;text-align:center;display:block;color:black;text-decoration:none;">Log In</a>	
		<br><br>		
		<a href="<%=request.getContextPath()%>/newUser" style="font-size:34px;text-align:center;display:block;color:black;text-decoration:none;">New User</a>
	</div>
</body>
</html>