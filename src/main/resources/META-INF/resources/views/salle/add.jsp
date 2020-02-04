<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<body>
	<div class="container">
		<h1>creation d'un salle</h1>
		<form:form action="save" method="post" modelAttribute="salle">
			<div class="form-group">
	 			<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">envoyer</button>
				<a href="${ctx}/auth/salle/list" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>