<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="planListTable" keepStatus="true" name="plans"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="goal" titleKey="plan.goal" sortable="true" />
	<display:column>
		<a href="comment/list.do?planId=${row.id}"> <spring:message
				code="plan.comment" />
		</a>
	</display:column>

</display:table>

<a href="welcome/index.do"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="plan.cancel"/>"
	onclick="self.location.href = welcome/index.do" /></a>




