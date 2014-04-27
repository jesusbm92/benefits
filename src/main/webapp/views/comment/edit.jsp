<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="comment/edit.do" modelAttribute="comment">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="date" />
	<form:hidden path="version" />
	<form:hidden path="user" />
	<form:hidden path="plan" />

	<acme:textarea code="comment.content" path="content" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="comment.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-default" name="delete"
			value="<spring:message code="comment.delete"/>"
			onclick="return confirm('<spring:message code="gallery.confirm.delete"/>')" />
	</jstl:if>

	<a href="comment/list.do?planId=${planId}"><input type="button"
		class="btn btn-default"
		value="<spring:message code="comment.cancel"/>" id="cancelar"
		name="cancelar" onclick="self.location.href = comment/list.do?planId=${planId}" /></a>


</form:form>

