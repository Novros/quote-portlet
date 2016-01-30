<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--@elvariable id="quote" type="cz.novros.lif.quotes.portlet.entity.Quote"--%>
<%--@elvariable id="danger" type="String"--%>
<%@include file="../init.jspf"%>

<%@page import="static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*" %>

<div id="${ns}Random" class="container-fluid">

	<c:choose>
	    <c:when test="${danger} == GeneratorError">
	    </c:when>    
	    <c:otherwise>
	        <div class="row text-center">
		    	<h1 class="quote-text">"${quote.text}"</h1>
		    </div>
		    <div class="row text-right">
		    	<h2 class="quote-author">- ${quote.author}</h2>
		    </div>
	    </c:otherwise>
	</c:choose>


    
    <div class="buttons row text-right">
	    <a class="btn btn-default" href="<portlet:renderURL/>"><spring:message code="random_quote-next"/> </a>
	    <a class="btn btn-success" href="<portlet:renderURL/>"><spring:message code="random_quote-save"/> </a>
    </div>
</div>
