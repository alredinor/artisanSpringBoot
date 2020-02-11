<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
	bonjour ${pageContext.request.userPrincipal.name}
	<form action="${ctx}/logout" method="post"
		style="display: inline-block;">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<button type="submit" class="btn btn-link">deconnection</button>
	</form>
	${pageContext.request.userPrincipal}
</c:if>