<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="domain.Meals"%>


<display:table uid="mealListTable" keepStatus="true" name="meals"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column titleKey="meal.name" sortable="true">
		<spring:message code="meal.name.${row.name}" />
	</display:column>

	<display:column>
		<a href="amount/administrator/listDetails.do?mealId=${row.id}"> <spring:message
				code="meal.details" />
		</a>
	</display:column>

	<display:column>
		<a href="day/administrator/listDaysByMeal.do?mealId=${row.id}"> <spring:message
				code="meal.days" />
		</a>
	</display:column>

	<display:column>
		<a href="meal/administrator/edit.do?mealId=${row.id}"><input
			class="btn btn-sm btn-info" type="button"
			value="<spring:message code="meal.edit"/>"
			onclick="self.location.href = meal/administrator/edit.do?mealId=${row.id}" /></a>
	</display:column>

</display:table>


<security:authorize access="hasRole('ADMIN')">
	<a href="meal/administrator/create.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="meal.create"/>"
		onclick="self.location.href = meal/administrator/create.do" /></a>
</security:authorize>

<a href="meal/administrator/list.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="meal.cancel"/>"
	onclick="self.location.href = meal/administrator/list.do" /></a>




