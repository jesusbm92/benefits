<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="comment/edit.do" modelAttribute="comment">

	<!-- Poner todos los atributos, los no usados en oculto -->

	<form:hidden path="id" />
	<form:hidden path="date" />
	<form:hidden path="user" />
	<form:hidden path="plan" />
	
	<acme:textbox code="comment.content" path="content" />
	<br>
	
	<acme:submit name="save" code="comment.save"/>

	<jstl:if test="${!create}">
		<input type="submit" name="delete" value="<spring:message code="comment.delete"/>"  onclick="return confirm('<spring:message code="gallery.confirm.delete"/>')" />
	</jstl:if>
	
	<a href = "comment/list.do"><input type="button" value="<spring:message code="comment.cancel"/>"  
	id="cancelar" name="cancelar" onclick= "self.location.href = comment/list.do" /></a>
	
	
</form:form>

