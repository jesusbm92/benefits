<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">

	<display:table uid="trainingDayListTable" keepStatus="false"
		name="trainingDays" pagesize="5" class="table table-hover"
		requestURI="${requestURI}" id="row">

		<display:column titleKey="trainingDay.name" sortable="true">
			<spring:message code="trainingDay.name.${row.name}" />
		</display:column>
		<display:column titleKey="trainingDay.edit">
			<a href="trainingDay/administrator/edit.do?trainingDayId=${row.id}"><input
				type="button" class="btn btn-default"
				value="<spring:message
				code="trainingDay.edit" />" /> </a>
		</display:column>
		<display:column titleKey="trainingDay.exerciseGroups">
			<a
				href="exerciseGroup/administrator/listExerciseGroup.do?trainingDayId=${row.id}"><input
				type="button" class="btn btn-default"
				value="<spring:message
				code="trainingDay.exerciseGroups" />" />
			</a>
		</display:column>

	</display:table>

	<a href="trainingDay/administrator/create.do"><input type="button"
		class="btn btn-default"
		value="<spring:message code="trainingDay.create"/>"
		onclick="self.location.href = trainingDay/administrator/create.do" /></a>
	<a href="training/administrator/list.do"> <input type="button"
		class="btn btn-default"
		value="<spring:message code="trainingDay.cancel"/>"
		onclick="self.location.href = training/administrator/list.do" /></a>
</div>