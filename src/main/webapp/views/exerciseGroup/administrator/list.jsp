<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container">

<display:table uid="exerciseGroupListTable" keepStatus="false" name="exerciseGroups"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">

	<display:column property="name" titleKey="exerciseGroup.name"
		sortable="true" />
	<display:column >
		<a href="exerciseGroup/administrator/edit.do?exerciseGroupId=${row.id}"> <input type="button"
					class="btn btn-default" value="<spring:message
				code="exerciseGroup.edit" />"/>
		</a>
	</display:column>
	<display:column titleKey="exerciseGroup.exercises">
		<a href="exercise/administrator/listByExerciseGroup.do?exerciseGroupId=${row.id}"> <input type="button"
					class="btn btn-default" value="<spring:message
				code="exerciseGroup.exercises.fil" />"/>
		</a>
	</display:column>
	
</display:table>

<a href="exerciseGroup/administrator/create.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="exerciseGroup.create"/>"
					onclick="self.location.href = exerciseGroup/administrator/create.do" /></a>

<input type="button" class="btn btn-default"
		value="<spring:message code="exerciseGroup.cancel"/>" onclick="history.back()" />

</div>