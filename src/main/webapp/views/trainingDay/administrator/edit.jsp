<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h1 class="text-center">
	<spring:message code="trainingDay.details" />
</h1>
<br/>

<div class="container">
	<form:form action="trainingDay/administrator/edit.do"
		modelAttribute="trainingDay">

		<!-- Poner todos los atributos, los no usados en oculto -->

		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="name">
			<spring:message code="trainingDay.name" />
		</form:label>
		<form:select path="name">
			<form:options items="${days}" />
		</form:select>
		<form:errors path="name" cssClass="error" />
		<br>

		<form:label path="exerciseGroups">
			<spring:message code="trainingDay.exerciseGroups" />
		</form:label>
		<form:select multiple="${exerciseGroups.size()}"
			items="${exerciseGroups}" itemLabel="name" id="id"
			code="trainingDay.exerciseGroups" path="exerciseGroups" />
		<form:errors path="exerciseGroups" cssClass="error" />
		<br>
		<br>
		<input type="submit" name="save" class="btn btn-default"
			value="<spring:message code="trainingDay.save" />" />

		<jstl:if test="${trainingDay.id!=0}">
			<a class="btn btn-default" data-toggle="modal"
				data-target="#basicModal"><spring:message
					code="trainingDay.delete" /></a>

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="trainingDay.confirm.title" />
							</h4>
						</div>
						<div class="modal-body">
							<h3>
								<spring:message code="trainingDay.confirm.body" />
							</h3>
						</div>
						<div class="modal-footer">
							<button type="submit" name="delete" class="btn btn-default"
								onclick="history.back()">
								<spring:message code="trainingDay.confirm.yes" />
							</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<spring:message code="trainingDay.confirm.no" />
							</button>
						</div>
					</div>
				</div>
			</div>


		</jstl:if>

		<input type="button" class="btn btn-default"
			value="<spring:message code="trainingDay.cancel"/>"
			onclick="history.back()" />


	</form:form>
</div>
