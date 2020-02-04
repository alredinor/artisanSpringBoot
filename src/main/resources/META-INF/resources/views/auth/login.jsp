<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<h1>connection</h1>
		<div>
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">erreur d'authentification</div>
			</c:if>
			<form method="post" action="">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
				<div class="form-group">
					<label for="username">login:</label> <input id="username"
						name="username" class="form-control">
				</div>
				<div class="form-group">
					<label for="password">password:</label><input type="password"
						id="password" name="password" class="form-control">
				</div>
				<div>
					<button type="submit" class="btn btn-info">envoyer</button>
					<a href="${ctx}" class="btn btn-link">annuler</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>