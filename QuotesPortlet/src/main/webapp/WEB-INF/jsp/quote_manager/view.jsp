<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../init.jspf"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="static cz.novros.lif.quotes.portlet.manager.QuotesManagerConstants.*" %>

<portlet:defineObjects />

<div id="${ns}Manager class="container-fluid">

	<c:if test="${successMessage != null}">
		<div class="alert alert-success text-center">
			${successMessage}
		</div>
	</c:if>

	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger text-center">
				${errorMessage}
		</div>
	</c:if>
	
	<c:choose>
	    <c:when test="${quotes_size == 0}">
	    	<div class="alert alert-info text-center">
				<spring:message code="manager_quote-empty_list"/>
			</div>
	    </c:when>    
	    <c:otherwise>
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
					<c:forEach var="quote" items="${quotes}" varStatus="iterator">
						<tr>
							<td>${quote.id}</td>
							<td>${quote.text}</td>
							<td>${quote.author}</td>
							<td>${quote.created}</td>
							<td>${quote.authorOfEntity}</td>
							<td class="text-center">
								<a class="btn btn-warning edit-button"><spring:message code="quote_manager-edit"/></a>
			    				<portlet:actionURL var="deleteActionUrl" name="<%=DELETE_ACTION%>">
									<portlet:param name="<%=PARAMETER_QUOTE_ID%>" value="${quote.id}"/>
								</portlet:actionURL>
			    				<a class="btn btn-danger" href="${deleteActionUrl}"><spring:message code="quote_manager-delete"/></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
	
	<div class="buttons text-right">
		<a id="add_button" class="btn btn-success"><spring:message code="quote_manager-add"/></a>
	</div>
	
	<portlet:actionURL var="addActionUrl" name="<%=ADD_ACTION%>"/>
	<form:form id="add_quote_form" action="${addActionUrl}" method="POST" modelAttribute="<%=FORM_MODEL%>" style="display: none;">
		<form:hidden path="id"/>
		<table width="100%">
			<tr>
				<td>
					<form:label path="text">Text</form:label>
					<form:input path="text" cssStyle="width: 95%;"/>
					<form:errors path="text" cssStyle="width: 88%;" cssClass="alert alert-danger"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="author">Author</form:label>
					<form:input path="author" cssStyle="width: 95%;"/>
					<form:errors path="author" cssStyle="width: 88%;" cssClass="alert alert-danger"/>
				</td>
			</tr>
		</table>

		<input type="submit" class="btn btn-primary" value="Submit" />
		<input id="add-form-button-cancel" class="btn btn-default" value="Cancel" />
	</form:form>
</div>

<script>
	$("#add_button").click(function(){
		var form = $("#add_quote_form");
		form.toggle();
	});
	$("#add-form-button-cancel").click(function(){
		var form = $("#add_quote_form");
		form.toggle();
	});
</script>
