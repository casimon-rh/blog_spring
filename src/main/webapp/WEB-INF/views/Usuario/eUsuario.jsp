<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form name="form" commandName="Usuario" method="post" action="${pageContext.request.contextPath}/usuario/editar/do">
	<div>
		<label>Nombre</label><form:input type="text" path="nombre"/>	 
	</div>
	<br>
	<div>
		<label>Email</label><form:input path="email"/>	 
	</div>
	<br>
	<div>
		<label>Password</label><form:input path="Password"/>	 
	</div>
	<form:hidden path="id" />
	<br>
	<input type="submit"/>
</form:form>
<br><br><br>
	<button type="submit" formaction="${pageContext.request.contextPath}/logout">LOGOUT</button>
</body>
</html>