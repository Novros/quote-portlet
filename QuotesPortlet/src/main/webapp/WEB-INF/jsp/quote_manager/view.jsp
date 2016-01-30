<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@include file="../init.jspf"%>

<%@page import="static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.*" %>

<div id="${ns}Manager class="container-fluid">

	<c:if test="${successMessage != null}">
		<div class="alert alert-success">
			${successMessage}
		</div>
	</c:if>

	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger">
				${errorMessage}
		</div>
	</c:if>

	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>id</th>
				<th>Quote</th>
				<th>Author</th>
				<th>Created</th>
				<th>Author of entity</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>id</th>
				<th>Quote</th>
				<th>Author</th>
				<th>Created</th>
				<th>Author of entity</th>
				<th>Actions</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="quote" items="${quote_list}" varStatus="iterator">
				<tr>
					<td>${quote.id}</td>
					<td>${quote.text}</td>
					<td>${quote.author}</td>
					<td>${quote.created}</td>
					<td>${quote.authorOfEntity}</td>
					<td class="text-center">
						<a class="btn btn-warning" href="<portlet:renderURL/>"><spring:message code="quote_manager-edit"/></a>
	    				<a class="btn btn-danger" href="<portlet:renderURL/>"><spring:message code="quote_manager-delete"/></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="buttons text-right">
		<a class="btn btn-success" href="<portlet:renderURL/>"><spring:message code="quote_manager-add"/></a>
	</div>
</div>
