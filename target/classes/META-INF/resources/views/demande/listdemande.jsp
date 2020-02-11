<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
		<link rel="stylesheet" href="${ctx }/bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
		<title>Liste demande</title>
	</head>
	<body>
		
		<div class="container">
			<div>
				<a href="${ctx}/demande/addDemande" class="btn btn-link">Ajout demande</a>
			</div>
			<table class="table">
			  <tr>
			    <th>Id demande</th>
			    <th>Message</th>
			    <th>Artisan</th>
			    <th>Client</th>
			    <th>Service</th>
			    <th>Metier</th>
			    <th>Statut</th>
			    <th>Date</th>
			    <th></th>
			    <th></th>
			  </tr>
			  <c:forEach items="${demandes}" var="d">
				  <tr>
				    <td>${d.idDemande}</td>
				    <td>${d.message}</td>
				    <td>${d.artisan}</td>
				    <td>${d.client}</td>
				    <td>${d.service}</td>
				    <td>${d.metier}</td>
				    <td>${d.statut}</td>
				    <td>${d.date}</td>
				    <td><a href="${ctx }/demande/editdemande?idDemande=${d.idDemande}" class="btn btn-info">Editer</a></td>
					<td><a href="${ctx }/demande/delete?idDemande=${d.idDemande}" class="btn btn-warning">Supprimer</a></td>
				  </tr>	
			  </c:forEach>
			</table>
		</div>
	</body>
</html>