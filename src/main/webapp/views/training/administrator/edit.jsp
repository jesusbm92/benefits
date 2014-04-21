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

		<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="training.save" />" />

	<jstl:if test="${training.id!=0}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="training.delete"/>"
			onclick="return confirm('<spring:message code="training.delete.confirm"/>')" />
	</jstl:if>

	<a href="training/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="training.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = training/administrator/list.do" /></a>


</form:form>

