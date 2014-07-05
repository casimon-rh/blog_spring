<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div>
			<button>Destruir Aplicacion</button>
		</div>
	</sec:authorize>	
	<c:url var="urlGuardar" value="/blog/guardar" />
	<form:form method="post" name="formulario" action="${urlGuardar}"
		commandName="post">
		<div>
			<Label><spring:message code="titulo" /></Label><br />
			<form:input path="titulo" />
		</div>
		<div>
			<form:errors path="titulo" />
		</div>
		<div>
			<label><spring:message code="contenido" /></label><br />
			<form:textarea path="contenido" />
		</div>
		<div>
			<form:errors path="contenido" />
		</div>
		<form:hidden path="id" />
		<input type="submit" />
	</form:form>
	<br>
	<br>
	<br>
	<form action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="logout" />
	</form>
</body>
</html>