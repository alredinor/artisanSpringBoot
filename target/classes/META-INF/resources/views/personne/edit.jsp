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
		<h1>edition personne</h1>
		<c:choose>
			<c:when test="${personne.getClass().simpleName == 'Formateur' }">
				<c:url var="action" value="/personne/saveFormateur"></c:url>
			</c:when>
			<c:otherwise>
				<c:url var="action" value="/personne/saveEleve"></c:url>
			</c:otherwise>
		</c:choose>
		<form:form action="${action}" method="get" modelAttribute="personne">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" cssClass="form-control" readonly="true"
					placeHolder="numero généré automatiquement" />
			</div>
			<div class="form-group">
				<form:label path="civilite">civilite:</form:label>
				<form:select cssClass="form-control" path="civilite"
					items="${civilites}" itemLabel="francais">
				</form:select>
			</div>
			<div class="form-group">
				<form:label path="prenom">prenom:</form:label>
				<form:input path="prenom" cssClass="form-control" />
				<form:errors path="prenom">
					<div class="alert alert-danger">bouhhhhh</div>
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
				<form:errors path="nom" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="dtNaiss">date de naissance:</form:label>
				<form:input type="date" path="dtNaiss" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.numero">numero:</form:label>
				<form:input type="number" path="adresse.numero"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.rue">rue:</form:label>
				<form:input path="adresse.rue" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.codePostal">code postal:</form:label>
				<form:input path="adresse.codePostal" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" cssClass="form-control" />
			</div>
			<c:choose>
				<c:when test="${personne.getClass().simpleName == 'Formateur' }">
					<div class="form-group">
						<form:label path="experience">experience:</form:label>
						<form:input type="number" path="experience"
							cssClass="form-control" />
					</div>
					<div class="form-group">
						<form:label path="referent">referent:</form:label>
						<form:checkbox path="referent" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<form:label path="formation">formation:</form:label>
						<form:input path="formation" cssClass="form-control" />
					</div>
				</c:otherwise>
			</c:choose>

			<div class="form-group">
				<form:label path="salle">salle:</form:label>
				<form:select path="salle.nom" cssClass="form-control">
					<form:option value="">pas de salle</form:option>
					<form:options items="${salles}" itemValue="nom" itemLabel="nom" />
				</form:select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">envoyer</button>
				<a href="${ctx}/personne/list" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>