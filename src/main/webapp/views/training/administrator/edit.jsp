<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@page import="domain.Goals"%>

<form:form action="training/administrator/edit.do" modelAttribute="training">

	<!-- Poner todos los atributos, los no usados en oculto -->

		<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="training.name" path="name" />
	<br>
	<acme:textbox code="training.duration" path="duration" />
	<br>
	
	<form:label path="sponsor">
		<spring:message code="training.sponsor" />
	</form:label>
	<form:select id="sponsors" path="sponsor">
	<form:option value="0" label="----" />
	<form:options items="${sponsors }" itemValue="id" itemLabel="name" />
	</form:select>
	<br>
	
	<form:label path="trainingDays">
		<spring:message code="training.trainingDays" />
	</form:label>
	<form:select multiple="${trainingDays.size()}" items="${trainingDays}" itemLabel="name" id="id" code="training.trainingDays" path="trainingDays"/>
	<br>

		<input type="submit" name="save" class="btn btn-default"
		value="<spring:message code="training.save" />" />

	<jstl:if test="${training.id!=0}">
			
		<a class="btn btn-default" data-toggle="modal"
			data-target="#basicModal"><spring:message code="training.delete" /></a>

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="training.confirm.title" />
						</h4>
					</div>
					<div class="modal-body">
						<h3>
							<spring:message code="training.confirm.body" />
						</h3>
					</div>
					<div class="modal-footer">
						<button type="submit" name="delete" class="btn btn-default"
							onclick="history.back()">
							<spring:message code="training.confirm.yes" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="training.confirm.no" />
						</button>
					</div>
				</div>
			</div>
		</div>
			
	</jstl:if>

	<a href="training/administrator/list.do"><input type="button"
		class="btn btn-default"
		value="<spring:message code="training.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = training/administrator/list.do" /></a>


</form:form>

