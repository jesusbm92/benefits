<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="exerciseGroup/administrator/edit.do" modelAttribute="exerciseGroup">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="exerciseGroup.name" path="name" />
	<br>
	
	<display:table uid="exercisesListTable" keepStatus="true" name="exercises"
	pagesize="10" class="table table-hover"
	id="row">


	<display:column property="name" titleKey="exercise.name" sortable="true" />
	<display:column property="repetitions" titleKey="exercise.repetitions" sortable="true" />
	<display:column property="cycles" titleKey="exercise.cycles" sortable="true" />
	<display:column property="muscle.name" titleKey="exercise.muscle" sortable="true" />
	
	<display:column>
		<a href="exerciseGroup/administrator/add.do?exerciseGroupId=exerciseGroup.id"> <spring:message
				code="exerciseGroup.edit" />
		</a>
	</display:column>
	

	
</display:table>
	


	<input type="submit" name="save" class="btn btn-sm btn-info"
		value="<spring:message code="exerciseGroup.save" />" />

	<jstl:if test="${!create}">
		<input type="submit" class="btn btn-sm btn-info" name="delete"
			value="<spring:message code="exerciseGroup.delete"/>"
			onclick="return confirm('<spring:message code="exerciseGroup.delete.confirm"/>')" />
	</jstl:if>

	<a href="exercise/administrator/list.do"><input type="button"
		class="btn btn-sm btn-info"
		value="<spring:message code="exercise.cancel"/>" id="cancelar"
		name="cancelar"
		onclick="self.location.href = exercise/administrator/list.do" /></a>


</form:form>

