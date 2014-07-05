<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<h1><spring:message code="post"/> <spring:message code="actual"/></h1>
	<h2><c:out value="${post.titulo}"/></h2>
	<p><c:out value="${post.contenido}"/></p>
	<br>
	<h2><spring:message code="comentarios"/>:</h2>
	<c:forEach var="coment" items="${post.comentarios}">
		<h4><c:out value="${coment.comentarista}"/> dice:</h4> 
		<p> <br>	<c:out value="${coment.comentario}"/>	</p>
	</c:forEach>
</body>
</html>