<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">
	<div class="row">
		<div class="table-responsive">
			<display:table uid="dietListTable" keepStatus="false" name="diets"
				pagesize="5" class="table table-hover" requestURI="${requestURI}"
				id="row">


				<display:column property="name" titleKey="diet.name" sortable="true" />
				<display:column property="description" titleKey="diet.description"
					sortable="true" />
				<display:column property="sponsor.name" titleKey="diet.sponsor"
					sortable="true" />

				<display:column>
					<a href="day/administrator/listDaysByDiet.do?dietId=${row.id}">
						<input class="btn btn-default" type="button"
						value="<spring:message code="diet.days"/>"
						onclick="self.location.href = diet/administrator/listDaysByDiet.do?dietId=${row.id}" />
					</a>
				</display:column>

				<display:column>
					<a href="diet/administrator/details.do?dietId=${row.id}"><input
						class="btn btn-default" type="button"
						value="<spring:message code="diet.details"/>"
						onclick="self.location.href = diet/administrator/details.do?dietId=${row.id}" /></a>
				</display:column>

				<display:column>
					<a href="diet/administrator/edit.do?dietId=${row.id}"><input
						class="btn btn-default" type="button"
						value="<spring:message code="diet.edit"/>"
						onclick="self.location.href = diet/administrator/edit.do?dietId=${row.id}" /></a>
				</display:column>

			</display:table>

			<security:authorize access="hasRole('ADMIN')">
				<a href="diet/administrator/create.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="diet.create"/>"
					onclick="self.location.href = diet/administrator/create.do" /></a>
			</security:authorize>

			<input type="button" class="btn btn-default"
				value="<spring:message code="diet.back"/>" onclick="history.back()" />

		</div>
	</div>
</div>