<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Days"%>
<%@page import="domain.Language"%>

<h1 class="text-center">
	<spring:message code="day.create.day" />
</h1>

<form:form action="day/administrator/edit.do" modelAttribute="day">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="day.name" />
	</form:label>
	<form:select path="name">
		<jstl:forEach var="name" items="${names}">
			<form:option value="${name}">
				<spring:message code="day.name.${name}" />
			</form:option>
		</jstl:forEach>
	</form:select>
	<form:errors path="name" cssClass="error" />
	<br>

	<acme:textbox code="day.descName" path="descriptiveName" />
	<br>

	<div class="form-group">
		<form:label path="language" class="col-md-4 control-label">
			<spring:message code="day.language" />
		</form:label>
		<div class="col-md-7">
			<form:select path="language" class="form-control">
				<jstl:forEach var="language" items="${languages}">
					<form:option value="${language}">
						<spring:message code="day.language.${language}" />
					</form:option>
				</jstl:forEach>
			</form:select>
			<form:errors cssClass="error" path="language"></form:errors>
			<br>
		</div>
	</div>


	<form:label path="meals">
		<spring:message code="day.meals.edit" />
	</form:label>
	<form:select multiple="${meals.size()}" items="${meals}"
		itemLabel="name" id="id" code="day.meal" path="meals" />
	<form:errors path="meals" cssClass="error" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="day.save" />" />

	<jstl:if test="${!create}">

		<a class="btn btn-default" data-toggle="modal"
			data-target="#basicModal"><spring:message code="day.delete" /></a>

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="day.confirm.title" />
						</h4>
					</div>
					<div class="modal-body">
						<h3>
							<spring:message code="day.confirm.body" />
						</h3>
					</div>
					<div class="modal-footer">
						<button type="submit" name="delete" class="btn btn-default"
							onclick="history.back()">
							<spring:message code="day.confirm.yes" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="day.confirm.no" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="day.cancel"/>" onclick="history.back()" />


</form:form>

