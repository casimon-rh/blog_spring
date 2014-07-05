<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="usuario"/></title>
</head>
<body>
	<h1><spring:message code="usuario"/></h1>
	<spring:message code="id"/>: <c:out value="${usr.id}"></c:out><br/>
	<spring:message code="nombre"/>: <c:out value="${usr.nombre}"></c:out><br/>
	Email: <c:out value="${usr.email}"></c:out><br/>
</body>
</html>