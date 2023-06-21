<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between my-5">
			<h1>Project Details</h1>
			<a href="/dashboard">Back to dashboard</a>
		</header>
		<div class="d-flex justify-content-start my-3" style="width:50%;">
			<p class="col">Project:</p>
			<p class="col"><c:out value="${ project.getTitle() }" /></p>
		</div>
		<div class="d-flex justify-content-start my-3" style="width:50%;">
			<p class="col">Description:</p>
			<p class="col"><c:out value="${ project.getDescription() }" /></p>
		</div>
		<div class="d-flex justify-content-start my-3" style="width:50%;">
			<p class="col">Due Date:</p>
			<p class="col"><fmt:formatDate pattern="MM/dd/YYYY" value="${ project.dueDate }"/></p>
		</div>
		<a href="/projects/${project.getId()}/tasks">See tasks</a>
	</div>
</body>
</html>