<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="domain.Days"%>


<display:table uid="dayListTable" keepStatus="false" name="days"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column titleKey="day.name" sortable="true">
		<spring:message code="day.name.${row.name}" />
	</display:column>

	<display:column>
		<a href="diet/administrator/listDietsByDay.do?dayId=${row.id}"> <spring:message
				code="day.diets" />
		</a>
	</display:column>
	<display:column>
		<a href="meal/administrator/listMealsByDay.do?dayId=${row.id}"> <spring:message
				code="day.meals" />
		</a>
	</display:column>

	<display:column>
		<a href="day/administrator/edit.do?dayId=${row.id}"><input
			class="btn btn-sm btn-info" type="button"
			value="<spring:message code="day.edit"/>"
			onclick="self.location.href = day/administrator/edit.do?dayId=${row.id}" /></a>
	</display:column>

</display:table>


<!-- <security:authorize access="hasRole('ADMIN')">
	<a href="day/administrator/create.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="day.create"/>"
		onclick="self.location.href = day/administrator/create.do" /></a>
</security:authorize>

<a href="day/administrator/list.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="day.cancel"/>"
	onclick="self.location.href = day/administrator/list.do" /></a> -->




