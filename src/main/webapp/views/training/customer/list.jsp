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
	<div class="col-md-10">

<jstl:if test="${res}">


	<display:table uid="planListTable" keepStatus="false" name="plans"
		pagesize="5" class="table table-hover" requestURI="${requestURI}"
		id="row">


		<display:column property="goal" titleKey="plan.goal" sortable="true" />
		<display:column>
			<a href="comment/list.do?planId=${row.id}"> <spring:message
					code="plan.comment" />
			</a>
		</display:column>
		<security:authorize access="hasRole('CUSTOMER')">
			<display:column>
				<a href="diet/customer/list.do?planId=${row.id}"> <spring:message
						code="plan.diet" />
				</a>
			</display:column>
			<display:column>
				<a href="training/customer/list.do?planId=${row.id}"> <spring:message
						code="plan.training" />
				</a>
			</display:column>
			<display:column>
				<a href="issue/customer/list.do?planId=${row.id}"> <spring:message
						code="plan.issue" />
				</a>
			</display:column>
		</security:authorize>

	</display:table>
</jstl:if>

<jstl:if test="${!res}">
	<a href="plan/customer/request.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="plan.request"/>"
		onclick="self.location.href = plan/customer/request.do" /></a>
</jstl:if>


<a href="welcome/index.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="plan.cancel"/>"
	onclick="self.location.href = welcome/index.do" /></a>

</div>
	<div class="col-md-2">
	
	</div>
</div>
</div>


