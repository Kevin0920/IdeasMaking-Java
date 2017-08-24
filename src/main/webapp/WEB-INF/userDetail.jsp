<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Detail</title>
<style>
.userDetail{
	border-style: dotted dashed solid double;
	text-align: center;
	font-style: italic;
	background-color: #b1c8ed;

}
</style>

</head>
<body>
	<h1>Poster Page</h1>
	<hr>
	<a href="/">Home Page</a>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
    <div class="userDetail"> 
    		<p>Name: ${user.firstname }</p>
		<p>Alias: ${user.alias }</p>
		<p>Email: ${user.email }</p>
    </div>
    
    
</body>
</html>