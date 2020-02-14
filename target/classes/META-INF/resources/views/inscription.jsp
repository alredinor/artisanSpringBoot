<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</head>
<body>
	<div class="container">
		<h1>inscription</h1>
		<form:form method="post" action="inscription" modelAttribute="login">
			<div class="form-group">
				<form:label path="login">login</form:label>
				<form:input path="login" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password">mot de passe</form:label>
				<form:input type="password" path="password" cssClass="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info">envoyer</button>
			</div>
		</form:form>
	</div>
</body>
</html>