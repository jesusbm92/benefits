<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table uid="exercisesListTable" keepStatus="true" name="exercises"
	pagesize="5" class="table table-hover" requestURI="${requestURI}"
	id="row">


	<display:column property="name" titleKey="exercise.name" sortable="true" />
	<display:column property="repetitions" titleKey="exercise.repetitions" sortable="true" />
	<display:column property="cycles" titleKey="exercise.cycles" sortable="true" />
	<display:column property="muscle.name" titleKey="exercise.muscle" sortable="true" />
	
	<display:column>
		<a href="exercise/administrator/edit.do?exerciseId=${row.id}"> <spring:message
				code="exercise.edit" />
		</a>
	</display:column>
	

	
</display:table>

	<a href="exercise/administrator/create.do" type="button"> <spring:message
				code="exercise.create" />
	</a>
