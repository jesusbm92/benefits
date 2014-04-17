<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="trainingListTable" keepStatus="true" name="trainings"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="name" titleKey="training.name" sortable="true" />
	<display:column property="duration" titleKey="training.duration" sortable="true" />
	<display:column property="sponsor.name" titleKey="training.sponsor" sortable="true" />
	<display:column>
		<a href="training/view.do?trainingId=${row.id}"> <spring:message
				code="training.view" />
		</a>
	</display:column>
</display:table>

<jstl:if test="${prin }">
<a href="training/administrator/create.do" type="button"> <spring:message
				code="training.create" />
	</a>
</jstl:if>


