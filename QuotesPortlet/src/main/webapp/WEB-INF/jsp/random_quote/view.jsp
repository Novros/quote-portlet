<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../init.jspf"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*" %>

<portlet:defineObjects />

<div id="${ns}Random" class="portlet-wrapper container-fluid">

	<c:choose>
	    <c:when test="${GeneratorError == null}">
	    	<div class="row text-center">
		    	<span class="quote-text">"${quote.text}"</span>
		    </div>
		    <div class="row text-right">
		    	<span class="quote-author">- ${quote.author}</span>
		    </div>
	    </c:when>    
	    <c:otherwise>
	        <div class="alert alert-danger">
				<spring:message code="random_quote-generator_error"/>
			</div>
	    </c:otherwise>
	</c:choose>
	
    <div class="buttons row text-right">
    	<portlet:actionURL var="nextActionUrl" name="<%=NEXT_ACTION%>"/>
	    <a class="btn btn-warning" href="${nextActionUrl}"><spring:message code="random_quote-next"/> </a>
	    <portlet:actionURL var="saveActionUrl" name="<%=SAVE_ACTION%>">
	    	<portlet:param name="<%=PARAM_QUOTE_TEXT%>" value="${quote.text}" />
	    	<portlet:param name="<%=PARAM_QUOTE_AUTHOR%>" value="${quote.author}" />
	    </portlet:actionURL>
	    <a class="btn btn-success" href="${saveActionUrl}"><spring:message code="random_quote-save"/> </a>
    </div>
</div>
