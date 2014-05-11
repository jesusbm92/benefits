<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="diet/administrator/edit.do" modelAttribute="diet">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="diet.name" />
	</form:label>
	<form:input path="name" type="text" />
	<form:errors path="name" cssClass="error" />
	<br />

	<acme:textarea path="description" code="diet.description" />
	<br />

	<div class="form-group">
		<form:label path="entityLanguage" class="col-md-4 control-label">
			<spring:message code="diet.language" />
		</form:label>
		<div class="col-md-7">
			<form:select path="entityLanguage" class="form-control">
				<jstl:forEach var="language" items="${languages}">
					<form:option value="${language}">
						<spring:message code="diet.language.${language}" />
					</form:option>
				</jstl:forEach>
			</form:select>
			<form:errors cssClass="error" path="entityLanguage"></form:errors>
			<br>
		</div>
	</div>

	<form:label path="sponsor">
		<spring:message code="diet.sponsor" />
	</form:label>
	<form:select id="sponsors" path="sponsor">
		<form:option value="0" label="----" />
		<form:options items="${sponsors }" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors path="sponsor" cssClass="error" />
	<br />

	<form:label path="days">
		<spring:message code="diet.days.edit" />
	</form:label>
	<form:select multiple="${days.size()}" items="${days}"
		itemLabel="descriptiveName" id="id" code="diet.day" path="days" />
	<form:errors path="days" cssClass="error" />
	<br />

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="diet.save" />" />

	<jstl:if test="${diet.id!=0}">

		<a class="btn btn-default" data-toggle="modal"
			data-target="#basicModal"><spring:message code="diet.delete" /></a>

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="diet.confirm.title" />
						</h4>
					</div>
					<div class="modal-body">
						<h3>
							<spring:message code="diet.confirm.body" />
						</h3>
					</div>
					<div class="modal-footer">
						<button type="submit" name="delete" class="btn btn-default"
							onclick="history.back()">
							<spring:message code="diet.confirm.yes" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="diet.confirm.no" />
						</button>
					</div>
				</div>
			</div>
		</div>

	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="diet.cancel"/>" onclick="history.back()" />


</form:form>

