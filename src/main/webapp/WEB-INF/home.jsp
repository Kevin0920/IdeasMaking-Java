<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style>
.messageArea{
	color:#00f;
	background-color:#ddd;
	border-style: solid;
	width: 300px;
	height: 100px;
	text-align: center;
	overflow: auto;
}
</style>
</head>
<body>
<h1>Welcome <c:out value="${currentUser.firstname}"></c:out></h1>
 <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
 </form>
 <hr>
<form:form method="POST" action="/idea" modelAttribute="idea">
<form:hidden path="poster" value = "${currentUser.id }"/>
    <p>
    		<form:label path="message">Idea:
	    <form:errors path="message"/>
	    <form:input path="message"  placeholder = "post something...."/></form:label>
	    
	    <input type="submit" value="Post an Idea!"/>
    </p>
    	<c:forEach var = "idea" items= "${ideas }">
    	<p><a href="/users/${idea.poster.id}">${idea.poster.alias}</a> Saying:</p>
    		<div class="messageArea">
    			<p>${idea.message}</p>
    		</div>
    		 	<p>
		    	<c:if test="${!idea.users.contains(currentUser) }">
		    		<a href = "/like/${idea.id }">Like</a>&nbsp;&nbsp;
		    	</c:if>
		    	<c:if test="${idea.users.contains(currentUser) }">
		    		Liked&nbsp;&nbsp;
		    	</c:if>
		    	<a href = "/ideas/${idea.id }">${idea.users.size()} people</a> like this&nbsp;&nbsp;
		    	<c:if test = "${idea.poster.id == currentUser.id }">
		    		<a href = "/delete/${idea.id }">Delete</a>
		    	</c:if>
	    	</p>
    	</c:forEach>
 

    
</form:form>

</body>
</html>