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
<title>New Project</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1 class="my-5">Create a Project</h1>
		<form:form action="/projects/new" method="post" modelAttribute="newProject" style="width:50%;" class="mx-auto">
		    <form:input type="hidden" value="${ userId }" path="leadUser"/>
		    <div class="my-3">
		        <form:label path="title" class="form-label">Project Title</form:label>
		        <form:errors path="title" style="color:red"/>
		        <form:input path="title" type="text" class="form-control"/>
		    </div>
		    <div class="my-3">
		        <form:label path="description" class="form-label">Project Description</form:label>
		        <form:errors path="description" style="color:red"/>
		        <form:input path="description" type="text" class="form-control"/>
		    </div>
		    <div class="my-3">
		        <form:label path="dueDate" class="form-label">Due Date</form:label>
		        <form:errors path="dueDate" style="color:red"/>
		        <form:input path="dueDate" class="form-control" type="date" style="width:120px;"/>
		    </div>
		    <a href="/dashboard" class="btn btn-outline-danger">Cancel</a>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>