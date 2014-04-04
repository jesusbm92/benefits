<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="issueListTable" keepStatus="true" name="issues"
	pagesize="5"  requestURI="${requestURI}" id="row">

	
	<display:column property="description" titleKey="issue.description" sortable="true" />
	<display:column property="plan.id" titleKey="issue.plan" sortable="true" />
	<display:column property="customer.name" titleKey="issue.user" sortable="true" />
	<display:column>
		<a href="issue/customer/edit.do?issueId=${row.id}"><input type="button" value="<spring:message code="issue.edit"/>"  
			 onclick="self.location.href = issue/customer/edit.do?issueId=${row.id}" /></a>
	</display:column>
	
</display:table>
			<a href="issue/customer/create.do?planId=${plan.id}"><input type="button" value="<spring:message code="issue.create"/>"  
			 onclick="self.location.href = issue/customer/create.do?planId=${plan.id}" /></a>

			<a href="plan/list.do"><input type="button" value="<spring:message code="issue.cancel"/>"  
			 onclick="self.location.href = plan/list.do" /></a>



