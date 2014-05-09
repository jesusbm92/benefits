<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="domain.Days"%>

<h1 class="text-center"><spring:message code="day.days" /></h1>

<div class="container">
	<display:table uid="dayListTable" keepStatus="false" name="days"
		pagesize="5" class="table table-hover" requestURI="${requestURI}"
		id="row">


		<display:column titleKey="day.name" sortable="true">
			<spring:message code="day.name.${row.name}" />
		</display:column>

		<display:column property="descriptiveName" titleKey="day.descName"
			sortable="true">
			<spring:message code="day.descName" />
		</display:column>

		<display:column>
			<a href="meal/administrator/listMealsByDay.do?dayId=${row.id}"> <input
				class="btn btn-default" type="button"
				value="<spring:message code="day.meals"/>"
				onclick="self.location.href = meal/administrator/listMealsByDay.do?dayId=${row.id}" />
			</a>
		</display:column>

		<display:column>
			<a href="day/administrator/edit.do?dayId=${row.id}"><input
				class="btn btn-default" type="button"
				value="<spring:message code="day.edit"/>"
				onclick="self.location.href = day/administrator/edit.do?dayId=${row.id}" /></a>
		</display:column>

	</display:table>

	<a href="day/administrator/create.do"><input
		class="btn btn-default" type="button"
		value="<spring:message code="day.create"/>"
		onclick="self.location.href = day/administrator/create.do" /></a> <a
		href="diet/administrator/list.do"><input type="button"
		class="btn btn-default" value="<spring:message code="diet.back"/>"
		onclick="self.location.href = diet/administrator/list.do" /></a>

</div>
<!--  Quitar los siguientes comentarios si es necesario crear un dia a partir de la lista de dias de una dieta -->

<!-- <security:authorize access="hasRole('ADMIN')">
	<a href="day/administrator/create.do"><input type="button"
		class="btn btn-default"
		value="<spring:message code="day.create"/>"
		onclick="self.location.href = day/administrator/create.do" /></a>
</security:authorize>

<a href="day/administrator/list.do"><input type="button"
	class="btn btn-default"
	value="<spring:message code="day.cancel"/>"
	onclick="self.location.href = day/administrator/list.do" /></a> -->




