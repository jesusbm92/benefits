<%--
 * panic.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <p><spring:message code="panic.text" /> <code>${name}</code>.</p>

<h2><spring:message code="panic.message" /></h2>

<p style="font-family: 'Courier New'">
	${exception}
</p> --%>
<p style="text-align:center"><spring:message code="panic.stack" /></span></p>
</br>

<p style="text-align:center;color: blue">
<a href="welcome/index.do" > <spring:message code="panic.href" /></a>
</p>