<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table uid="trainingDayListTable" keepStatus="true" name="trainingDays"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">

	<display:column  titleKey="trainingDay.name" sortable="true">
	<spring:message code="trainingDay.name.${row.name}" />
	</display:column>
	<display:column titleKey="trainingDay.training">
		<a href="training/administrator/listTraining.do?trainingDayId=${row.id}"> <spring:message
				code="trainingDay.training.fil" />
		</a>
	</display:column>
</display:table>
