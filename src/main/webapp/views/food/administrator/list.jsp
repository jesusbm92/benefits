<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="dietListTable" keepStatus="true" name="foods"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="name" titleKey="food.name" sortable="true" />
	<display:column property="description" titleKey="food.description"
		sortable="true" />

	<display:column>
		<a href="amount/list.do?foodId=${row.id}"> <spring:message
				code="food.amounts" />
		</a>
	</display:column>
	
	<display:column>
		<a href="food/administrator/edit.do?foodId=${row.id}"><input
			class="btn btn-sm btn-info" type="button"
			value="<spring:message code="food.edit"/>"
			onclick="self.location.href = food/administrator/edit.do?foodId=${row.id}" /></a>
	</display:column>

</display:table>


<security:authorize access="hasRole('ADMIN')">
	<a href="food/administrator/create.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="food.create"/>"
		onclick="self.location.href = food/administrator/create.do" /></a>
</security:authorize>

<a href="food/administrator/list.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="food.cancel"/>"
	onclick="self.location.href = food/administrator/list.do" /></a>




