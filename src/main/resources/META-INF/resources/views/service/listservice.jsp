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
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h1>
			liste de service
		</h1>
		<a href="${ctx}/service/addservice" class="btn btn-link">ajout de service</a>
		<table class="table">
			<tr>
				<th>Service</th>
				<th></th>
			</tr>
			<c:forEach items="${services}" var="s">
				<tr>
					<td>${s.nomService}</td>
					<td><a href="${ctx}/service/delete?idService=${s.idService}"
						class="btn btn-warning">effacer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>