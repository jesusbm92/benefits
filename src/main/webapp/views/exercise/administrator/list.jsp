<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1 class="text-center">
	<spring:message code="exercise.exercises" />
</h1>

<div class="container">
	<div class="row">
		<div class="table-responsive">

			<display:table uid="exercisesListTable" keepStatus="false"
				name="exercises" pagesize="10" class="table table-hover"
				requestURI="${requestURI}" id="row">


				<display:column property="name" titleKey="exercise.name"
					sortable="true" />
				<display:column property="repetitions"
					titleKey="exercise.repetitions" sortable="true" />
				<display:column property="cycles" titleKey="exercise.cycles"
					sortable="true" />

				<display:column property="entityLanguage"
					titleKey="exercise.language" sortable="true" />
				<display:column property="muscle.name" titleKey="exercise.muscle"
					sortable="true" />

				<display:column>
					<a href="exercise/administrator/edit.do?exerciseId=${row.id}"><input
						class="btn btn-default" type="button"
						value="<spring:message code="exercise.edit"/>"
						onclick="self.location.href = exercise/administrator/edit.do?exerciseId=${row.id}" /></a>
				</display:column>



			</display:table>
			<jstl:if test="${!other }">
				<a href="exercise/administrator/create.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="exercise.create"/>"
					onclick="self.location.href = exercise/administrator/create.do" /></a>
			</jstl:if>
			<%-- 
			<jstl:if test="${other }">
				<a href="exerciseGroup/administrator/list.do" type="button"> <spring:message
						code="exercise.cancel" />
				</a>
			</jstl:if> --%>
			<a href="exerciseGroup/administrator/list.do"> <input
				type="button" class="btn btn-default"
				value="<spring:message code="exercise.cancel"/>"
				onclick="self.location.href = exerciseGroup/administrator/list.do" /></a>


		</div>
	</div>
</div>