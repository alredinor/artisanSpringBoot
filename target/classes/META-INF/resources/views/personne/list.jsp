<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<div>
			<a href="${ctx}/personne/addFormateur" class="btn btn-link">ajout
				formateur</a> <a href="${ctx}/personne/addEleve" class="btn btn-link">ajout
				eleve</a>
		</div>
		<table class="table">
			<tr>
				<th>id</th>
				<th>civilite</th>
				<th>prenom</th>
				<th>nom</th>
				<th>date de naissance</th>
				<th>adresse</th>
				<th>code postal</th>
				<th>ville</th>
				<th>salle</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${personnes}" var="p">
				<tr>
					<td>${p.id}</td>
					<td>${p.civilite.francais}</td>
					<td>${p.prenom}</td>
					<td>${p.nom}</td>
					<td><fmt:formatDate value="${p.dtNaiss}" pattern="dd/MM/yyyy" /></td>
					<td>${p.adresse.numero}&nbsp;${p.adresse.rue}</td>
					<td>${p.adresse.codePostal}</td>
					<td>${p.adresse.ville}</td>
					<td>${p.salle.nom}</td>
					<td><a href="${ctx}/personne/edit?id=${p.id}"
						class="btn btn-info">editier</a></td>
					<td><sec:authorize access="hasAnyRole('ADMIN')">
							<a href="${ctx}/personne/delete?id=${p.id}"
								class="btn btn-danger">supprimer</a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>