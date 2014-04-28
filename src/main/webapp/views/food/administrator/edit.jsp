<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="food/administrator/edit.do" modelAttribute="food">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="amounts" />

	<form:label path="name">
		<spring:message code="food.name" />
	</form:label>
	<form:input path="name" type="text" />
	<form:errors path="name" cssClass="error" />
	<br>

	<acme:textarea path="description" code="food.description" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="food.save" />" />

	<jstl:if test="${!create}">


		<a class="btn btn-default" data-toggle="modal"
			data-target="#basicModal"><spring:message code="food.delete" /></a>

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="food.confirm.title" />
						</h4>
					</div>
					<div class="modal-body">
						<h3>
							<spring:message code="food.confirm.body" />
						</h3>
					</div>
					<div class="modal-footer">
						<button type="submit" name="delete" class="btn btn-default"
							onclick="history.back()">
							<spring:message code="food.confirm.yes" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="food.confirm.no" />
						</button>
					</div>
				</div>
			</div>
		</div>

	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="food.cancel"/>" onclick="history.back()" />


</form:form>

