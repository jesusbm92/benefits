<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="commentListTable" keepStatus="true" name="comments"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="content" titleKey="comment.content"
		sortable="true" />
	<display:column property="plan.id" titleKey="comment.plan"
		sortable="true" />
	<display:column property="user.name" titleKey="comment.user"
		sortable="true" />


</display:table>



<a href="comment/create.do?planId=${plan.id}"><input type="button"
	class="btn btn-sm btn-info"
	value="<spring:message code="comment.create"/>"
	onclick="self.location.href = comment/create.do" /></a>

<security:authorize access="hasRole('CUSTOMER')">
	<a href="plan/customer/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="comment.cancel"/>"
		onclick="self.location.href = plan/customer/list.do" /></a>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<a href="plan/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="comment.cancel"/>"
		onclick="self.location.href = plan/administrator/list.do" /></a>
</security:authorize>






