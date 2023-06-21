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
		<header class="my-5">
			<div class="d-flex justify-content-between align-items-center my-3">				
				<h1>Project: <c:out value="${project.getTitle()}"/></h1>
				<a href="/dashboard">Dashboard</a>
			</div>
			<h4>Project Lead: <c:out value="${ project.getLeadUser().getFirstName() }"/></h4>
		</header>
		<div class="" style="width:60%">			
			<form:form action="/projects/tasks/new" method="post" modelAttribute="newTask">
			    <form:input type="hidden" value="${ userId }" path="user"/>
			    <form:input type="hidden" value="${ project.getId() }" path="project"/>
			    <div class="my-3">
			        <form:label path="description" class="form-label">Add a task for this project:</form:label><br/>
			        <form:errors path="description" style="color:red"/>
			        <form:textarea path="description" class="form-control" rows="4"/>
			    </div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
			<div class="my-5">
				<ul>
					<c:forEach var="task" items="${ tasks }">
						<p class="fw-bold">Added by <c:out value="${ task.getUser().getFirstName() }"/> at <fmt:formatDate pattern="hh:mmaa MMM dd" value="${ task.getCreatedAt() }"/>:</p>
						<p><c:out value="${ task.getDescription() }"/></p>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>