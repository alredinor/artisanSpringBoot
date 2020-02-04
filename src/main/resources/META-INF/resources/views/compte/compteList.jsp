<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/bootstrap/css/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<a href="${ctx}/compte/addArtisan" class="btn btn-link">ajout Artisan</a>
			<a href="${ctx}/compte/addClient" class="btn btn-link">ajout Client</a>
		</div>
		<table class="table">
			<tr>
				<th>idCompte</th>
				<th>login</th>
				<th>mot de passe</th>
				<th>adresse</th>
				<th>code postal</th>
				<th>ville</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${compte}" var="c">
				<tr>
					<td>${c.idCompte}</td>
					<td>${c.login}</td>
					<td>${c.mdp}</td>
	
					<td>${c.adresse.numero}&nbsp;${c.adresse.rue}</td>
					<td>${c.adresse.codePostal}</td>
					<td>${c.adresse.ville}</td>
					<td><a href="${ctx}/compte/compteEdit?idCompte=${c.idCompte}" class="btn btn-info">editer</a></td>
					<td><a href="${ctx}/compte/delete?idCompte=${c.idCompte}" class="btn btn-danger">supprimer</a></td>
				</tr>
			</c:forEach>
			</table>
	</div>
</body>
</html>