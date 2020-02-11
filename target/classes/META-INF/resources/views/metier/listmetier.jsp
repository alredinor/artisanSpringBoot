<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		
		
		<a href="${ctx}/metier/addmetier" class="btn btn-link">ajout d'un metier</a>
		<table class="table">
			<tr>
				<th>id</th>
				<th>metier</th>
				<th></th>
			</tr>
			<c:forEach items="${metiers}" var="m">
				<tr>
					<td>${m.idMetier}</td>
					<td>${m.titreMetier}</td>
					<td><a href="${ctx}/metier/delete?idMetier=${m.idMetier}"
						class="btn btn-warning">supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>