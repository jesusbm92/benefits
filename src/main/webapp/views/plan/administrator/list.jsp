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

<div class="container">
	<div class="row">
		<div class="table-responsive">
			<display:table uid="planListTable" keepStatus="true" name="plans" 
				pagesize="5" requestURI="${requestURI}" id="row">


				<display:column property="goal" titleKey="plan.goal" sortable="true" />
				<display:column>
					<a href="comment/list.do?planId=${row.id}"> <spring:message
							code="plan.comment" />
					</a>
				</display:column>
				<display:column>
					<a href="issue/administrator/list.do?planId=${row.id}"> <spring:message
							code="plan.issue" />
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

			<a href="plan/administrator/list.do"><input type="button"
				class="btn btn-default" value="<spring:message code="plan.cancel"/>"
				onclick="self.location.href = plan/administrator/list.do" /></a>
		</div>
	</div>
</div>


