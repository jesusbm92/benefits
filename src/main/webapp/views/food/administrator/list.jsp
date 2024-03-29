<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1 class="text-center"><spring:message code="food.foods" /></h1>

<div class="container">
	<display:table uid="foodListTable" keepStatus="false" name="foods"
		pagesize="5" class="table table-hover" requestURI="${requestURI}"
		id="row">


		<display:column property="name" titleKey="food.name" sortable="true" />
		<display:column property="description" titleKey="food.description"
			sortable="true" />

		<display:column>
			<a href="food/administrator/edit.do?foodId=${row.id}"><input
				class="btn btn-default" type="button"
				value="<spring:message code="food.edit"/>"
				onclick="self.location.href = food/administrator/edit.do?foodId=${row.id}" /></a>
		</display:column>

	</display:table>


	<security:authorize access="hasRole('ADMIN')">
		<a href="food/administrator/create.do"><input type="button"
			class="btn btn-default" value="<spring:message code="food.create"/>"
			onclick="self.location.href = food/administrator/create.do" /></a>
	</security:authorize>
	<a href="meal/administrator/list.do"> <input type="button"
		class="btn btn-default" value="<spring:message code="food.back"/>"
		onclick="self.location.href = meal/administrator/list.do" /></a>

</div>

