<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">

	<h1 class="text-center">
		<spring:message code="amount.food" />
	</h1>
	<br/>
	<display:table uid="amountListTable" keepStatus="false" name="amounts"
		pagesize="5" class="table table-hover" requestURI="${requestURI}"
		id="row">



		<display:column property="food.name" titleKey="food.name"
			sortable="true" />
		<display:column property="food.description"
			titleKey="food.description" sortable="true" />
		<display:column property="quantity" titleKey="amount.quantity"
			sortable="true" />
		<display:column property="measure" titleKey="amount.measure"
			sortable="true" />

		<display:column>
			<a
				href="amount/administrator/edit.do?amountId=${row.id}&mealId=${param.mealId}"><input
				class="btn btn-default" type="button"
				value="<spring:message code="amount.edit"/>"
				onclick="self.location.href = amount/administrator/edit.do?amountId=${row.id}&mealId=${param.mealId}" /></a>
		</display:column>

	</display:table>

	<security:authorize access="hasRole('ADMIN')">
		<a href="amount/administrator/create.do?mealId=${param.mealId }"><input
			type="button" class="btn btn-default"
			value="<spring:message code="amount.create"/>"
			onclick="self.location.href = amount/administrator/create.do" /></a>
	</security:authorize>
	<a href="meal/administrator/list.do"> <input type="button"
		class="btn btn-default" value="<spring:message code="amount.back"/>"
		onclick="self.location.href = meal/administrator/list.do" /></a>

</div>

