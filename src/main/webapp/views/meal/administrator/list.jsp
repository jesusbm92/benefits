<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="domain.Meals"%>

<h1 class="text-center">
	<spring:message code="meal.meals" />
</h1>
<br/>

<div class="container">
<div class="row">
<div class="col-md-10">
	<display:table uid="mealListTable" keepStatus="false" name="meals"
		pagesize="5" class="table table-hover" requestURI="${requestURI}"
		id="row">


		<display:column titleKey="meal.name" sortable="true">
			<spring:message code="meal.name.${row.name}" />
		</display:column>
		<display:column property="description" titleKey="meal.description"
			sortable="true" />


		<display:column>
			<a href="amount/administrator/listDetails.do?mealId=${row.id}"> <input
				class="btn btn-default" type="button"
				value="<spring:message code="meal.details"/>"
				onclick="self.location.href = amount/administrator/listDetails.do?mealId=${row.id}" />
			</a>
		</display:column>

		<display:column>
			<a href="meal/administrator/edit.do?mealId=${row.id}"><input
				class="btn btn-default" type="button"
				value="<spring:message code="meal.edit"/>"
				onclick="self.location.href = meal/administrator/edit.do?mealId=${row.id}" /></a>
		</display:column>

	</display:table>


	<security:authorize access="hasRole('ADMIN')">
		<a href="meal/administrator/create.do"><input type="button"
			class="btn btn-default" value="<spring:message code="meal.create"/>"
			onclick="self.location.href = meal/administrator/create.do" /></a>
	</security:authorize>
	<a href="day/administrator/list.do"> <input type="button"
		class="btn btn-default" value="<spring:message code="meal.back"/>"
		onclick="self.location.href = day/administrator/list.do" /></a>

</div>
</div>
</div>