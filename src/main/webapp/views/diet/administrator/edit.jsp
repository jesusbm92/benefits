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
	<br>

	<acme:textarea path="description" code="diet.description" />
	<br>


	<form:select id="sponsors" path="sponsor">
		<form:option value="0" label="----" />
		<form:options items="${sponsors }" itemValue="id" itemLabel="name" />
	</form:select>
	<br>

	<form:select multiple="${days.size()}" items="${days}" itemLabel="name"
		id="id" code="diet.day" path="days" />
	<br>

	<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="diet.save" />" />

	<jstl:if test="${diet.id!=0}">
		<!-- <input type="submit" class="btn btn-default" data-toggle="modal"
			data-target="#basicModal" name="delete" value="" /> -->
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
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<spring:message code="diet.confirm.no" />
						</button>
						<button type="submit" name="delete" class="btn btn-primary"
							onclick="history.back()">
							<spring:message code="diet.confirm.yes" />
						</button>
					</div>
				</div>
			</div>
		</div>

	</jstl:if>

	<input type="button" class="btn btn-default"
		value="<spring:message code="diet.cancel"/>" onclick="history.back()" />


</form:form>

