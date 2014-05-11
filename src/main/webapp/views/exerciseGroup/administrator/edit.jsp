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
	<spring:message code="exerciseGroup.details" />
</h1>
<br />

<div class="container">
	<form:form action="exerciseGroup/administrator/edit.do"
		modelAttribute="exerciseGroup">

		<!-- Poner todos los atributos, los no usados en oculto -->

		<form:hidden path="id" />
		<form:hidden path="version" />

		<acme:textbox code="exerciseGroup.name" path="name" />
		<br>
		<acme:textbox code="exerciseGroup.description" path="description" />
		<br>

		<div class="form-group">
			<form:label path="entityLanguage" class="col-md-4 control-label">
				<spring:message code="exerciseGroup.language" />
			</form:label>
			<div class="col-md-7">
				<form:select path="entityLanguage" class="form-control">
					<jstl:forEach var="language" items="${languages}">
						<form:option value="${language}">
							<spring:message code="exerciseGroup.language.${language}" />
						</form:option>
					</jstl:forEach>
				</form:select>
				<form:errors cssClass="error" path="entityLanguage"></form:errors>
				<br>
			</div>
		</div>

		<form:label path="exercises">
			<spring:message code="exerciseGroup.exercises" />
		</form:label>
		<form:select multiple="${exercises.size()}" items="${exercises}"
			itemLabel="name" id="id" code="exerciseGroup.exercises"
			path="exercises" />
		<form:errors path="exercises" cssClass="error" />
		<br>
		<br>
		<input type="submit" name="save" class="btn btn-default"
			value="<spring:message code="exerciseGroup.save" />" />

		<jstl:if test="${exerciseGroup.id!=0}">

			<a class="btn btn-default" data-toggle="modal"
				data-target="#basicModal"><spring:message
					code="exerciseGroup.delete" /></a>

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">
								<spring:message code="exerciseGroup.confirm.title" />
							</h4>
						</div>
						<div class="modal-body">
							<h3>
								<spring:message code="exerciseGroup.confirm.body" />
							</h3>
						</div>
						<div class="modal-footer">
							<button type="submit" name="delete" class="btn btn-default"
								onclick="history.back()">
								<spring:message code="exerciseGroup.confirm.yes" />
							</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<spring:message code="exerciseGroup.confirm.no" />
							</button>
						</div>
					</div>
				</div>
			</div>

		</jstl:if>

		<input type="button" class="btn btn-default"
			value="<spring:message code="exerciseGroup.cancel"/>"
			onclick="history.back()" />


	</form:form>
</div>
