<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="amount/administrator/edit.do" modelAttribute="amount">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="meal" />

	<form:label path="quantity">
		<spring:message code="amount.quantity" />
	</form:label>
	<form:input path="quantity" type="text" />
	<form:errors path="quantity" cssClass="error" />
	<br>

	<form:label path="measure">
		<spring:message code="amount.measure" />
	</form:label>
	<form:input path="measure" type="text" />
	<form:errors path="measure" cssClass="error" />
	<br>

	<form:label path="food" for="foodId">
		<spring:message code="amount.food" />
	</form:label>
	<form:select id="foodId" path="food">
		<form:options items="${foods}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors path="food" cssClass="error" />

	</br>
	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="amount.save" />" />

	<jstl:if test="${!create}">			
			<a class="btn btn-default" data-toggle="modal"
			data-target="#basicModal"><spring:message code="amount.delete" /></a>

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="amount.confirm.title" />
						</h4>
					</div>
					<div class="modal-body">
						<h3>
							<spring:message code="amount.confirm.body" />
						</h3>
					</div>
					<div class="modal-footer">
						<button type="submit" name="delete" class="btn btn-default"
							onclick="history.back()">
							<spring:message code="amount.confirm.yes" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="amount.confirm.no" />
						</button>
					</div>
				</div>
			</div>
		</div>
			
	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="amount.cancel"/>"
		onclick="history.back()" />
</form:form>

