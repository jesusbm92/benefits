<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="date" class="java.util.Date" />

<hr class="featurette-divider">

<div class="container">
	<footer>
		<p class="pull-right">
			<a href="#">Back to top</a>
		</p>
		<p>
			Copyright &copy; <fmt:formatDate value="${date}"
					pattern="yyyy" /> Benefits Co. Inc. by Infit Solutions
			· <a href="#">About</a>
			 · <a href="#">Terms</a> 
		</p>
		<%-- <p class="text-center"><b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Benefits Co. Inc. by Infit Solutions</b></p> --%>
	</footer>
</div>
