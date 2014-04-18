<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="amountListTable" keepStatus="false" name="amounts"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="quantity" titleKey="amount.name"
		sortable="true" />
	<display:column property="measure" titleKey="amount.name"
		sortable="true" />
	
	<display:column>
		<a href="amount/administrator/edit.do?amountId=${row.id}"><input
			class="btn btn-sm btn-info" type="button"
			value="<spring:message code="amount.edit"/>"
			onclick="self.location.href = amount/administrator/edit.do?amountId=${row.id}" /></a>
	</display:column>

</display:table>


<security:authorize access="hasRole('ADMIN')">
	<a href="amount/administrator/create.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="amount.create"/>"
		onclick="self.location.href = amount/administrator/create.do" /></a>
</security:authorize>

<a href="amount/administrator/list.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="amount.cancel"/>"
	onclick="self.location.href = amount/administrator/list.do" /></a>




