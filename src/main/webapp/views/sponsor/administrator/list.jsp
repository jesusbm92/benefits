<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<style>
.col-centered {
	float: none;
	margin: 0 auto;
}
</style>

<h1 class="text-center">
	<spring:message code="sponsor.sponsors" />
</h1>
<br/>

<div class="container">
	<div class="row">
	<div class="col-md-10">
		<div class="table-responsive">
			<display:table uid="sponsorListTable" keepStatus="false"
				name="sponsors" pagesize="5" requestURI="${requestURI}" id="row">
				<display:column property="name" titleKey="sponsor.name"
					sortable="true" />
				<display:column property="address" titleKey="sponsor.address"
					sortable="true" />
				<display:column property="email" titleKey="sponsor.email"
					sortable="true" />
				<display:column property="phone" titleKey="sponsor.phone" />
				<display:column>
					<a href="sponsor/administrator/edit.do?sponsorId=${row.id}"><input
						class="btn btn-default" type="button"
						value="<spring:message code="sponsor.edit"/>"
						onclick="self.location.href = sponsor/administrator/edit.do?sponsorId=${row.id}" /></a>
				</display:column>

			</display:table>

		</div>
	</div>

	<div class="row">
		<div class="col-md-3 col-centered">
			<security:authorize access="hasRole('ADMIN')">
				<a href="sponsor/administrator/create.do"><input type="button"
					class="btn btn-default"
					value="<spring:message code="sponsor.create"/>"
					onclick="self.location.href = sponsor/administrator/create.do" /></a>
			</security:authorize>

			<a href="welcome/index.do"><input type="button"
				class="btn btn-default"
				value="<spring:message code="sponsor.cancel"/>"
				onclick="self.location.href = welcome/index.do" /></a>
		</div>
	</div>

</div>

</div>
