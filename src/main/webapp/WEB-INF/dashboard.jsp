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
		<header class="d-flex justify-content-between align-items-center my-5">
			<h1>Welcome, <c:out value="${ userName }" /></h1>
			<a href="/logout" class="text-danger">Log out</a>
		</header>
		<div class="my-3">
			<div class="d-flex justify-content-between align-items-center my-3">				
				<h3>All Projects</h3>
				<a href="/projects/new" class="btn btn-outline-primary">+ new project</a>
			</div>
			<table class="table text-center mb-5">
				<thead>
					<tr>
						<th scope="col">Project</th>
						<th scope="col">Team Lead</th>
						<th scope="col">Due Date</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ otherProjects }">
						<tr>
							<td><a href="/projects/${ project.getId() }"><c:out value="${ project.getTitle() }"/></a></td>
							<td><c:out value="${ project.leadUser.getFirstName() }"/> <c:out value="${ project.leadUser.getLastName() }"/></td>
							<td><fmt:formatDate pattern="MMM dd" value="${ project.dueDate }"/></td>
							<td>
								<form action="/${project.getId()}/addlink" method="post">
									<input type="hidden" name="_method" value="put">
							        <button type="submit" class="btn btn-success">Join Group</button>
							    </form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<div class="my-3">				
				<h3>Your Projects</h3>
			</div>
			<table class="table text-center">
				<thead>
					<tr>
						<th scope="col">Project</th>
						<th scope="col">Team Lead</th>
						<th scope="col">Due Date</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ yourProjects }">
						<tr>
							<td><a href="/projects/${ project.getId() }"><c:out value="${ project.getTitle() }"/></a></td>
							<td><c:out value="${ project.leadUser.getFirstName() }"/> <c:out value="${ project.leadUser.getLastName() }"/></td>
							<td><fmt:formatDate pattern="MMM dd" value="${ project.dueDate }"/></td>
							<c:choose>
								<c:when test="${ project.leadUser.getId().equals(userId) }">
									<td>
										<a href="/projects/${ project.getId() }/edit">Edit</a>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<form action="/${project.getId()}/leave" method="post">
    										<input type="hidden" name="_method" value="put">
									        <button type="submit" class="btn btn-danger">Leave Group</button>
									    </form>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>