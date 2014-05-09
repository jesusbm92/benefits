<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<style>
.col-centered {
	float: none;
	margin: 0 auto;
}
</style>

<h1 class="text-center"><spring:message code="plan.plans" /></h1>
<br/>

<div class="container">
	<div class="row">
		<div class="table-responsive">
			<display:table uid="planListTable" keepStatus="false" name="plans"
				pagesize="5" requestURI="${requestURI}" id="row">
				<display:column property="name" titleKey="plan.name"
				sortable="true" />
				<display:column titleKey="plan.goal" sortable="true">
					<spring:message code="plan.goal.${row.goal}" />
				</display:column>
				<display:column>
					<a href="comment/list.do?planId=${row.id}"> <input
						type="button" class="btn btn-default"
						value="<spring:message code="plan.comment" />" />
					</a>
				</display:column>
				<display:column>
					<a href="diet/administrator/details.do?dietId=${row.diet.id}"><input
						type="button" class="btn btn-default"
						value="<spring:message code="plan.showDiet" />" /> </a>
				</display:column>
				<display:column>
					<a href="training/details.do?trainingId=${row.training.id}"><input
						type="button" class="btn btn-default"
						value="<spring:message code="plan.showTraining" />" /> </a>
				</display:column>
				<display:column>
					<a href="issue/administrator/list.do?planId=${row.id}"> <input
						type="button" class="btn btn-default"
						value="<spring:message code="plan.issue" />" />
					</a>
				</display:column>
				<display:column>
					<a href="plan/administrator/edit.do?planId=${row.id}"><input
						class="btn btn-default" type="button"
						value="<spring:message code="plan.edit"/>"
						onclick="self.location.href = plan/administrator/edit.do?planId=${row.id}" /></a>
				</display:column>

			</display:table>

		</div>
	</div>

	<div class="row">
		<div class="col-md-3 col-centered">
			<security:authorize access="hasRole('ADMIN')">
				<a href="plan/administrator/create.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="plan.create"/>"
					onclick="self.location.href = plan/administrator/create.do" /></a>
			</security:authorize>

			<a href="welcome/index.do"><input type="button"
				class="btn btn-default" value="<spring:message code="plan.cancel"/>"
				onclick="self.location.href = welcome/index.do" /></a>
		</div>
	</div>

</div>


