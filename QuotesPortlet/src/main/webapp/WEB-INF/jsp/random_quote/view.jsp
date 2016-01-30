<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@include file="../init.jspf"%>

<%@page import="static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*" %>

<div id="${ns}Random" class="container-fluid">

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
	    <a class="btn btn-default" href="<portlet:renderURL/>"><spring:message code="random_quote-next"/> </a>
	    <a class="btn btn-success" href="<portlet:renderURL/>"><spring:message code="random_quote-save"/> </a>
    </div>
</div>
