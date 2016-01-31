<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../init.jspf"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<portlet:defineObjects />

<%
	boolean local_quotes_cfg = GetterUtil.getBoolean(portletPreferences.getValue("localQuotes", StringPool.TRUE));
%>

<div class="container">
	<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL"/>
	<aui:form class="form" action="${configurationURL}" method="post" name="<portlet:namespace />fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	
		<div class="checkbox">
			<label>
				<aui:input class="form-control" name="preferences--localQuotes--" type="checkbox" value="<%= local_quotes_cfg %>" />
			</label>
		</div>
	
	    <aui:button-row>
	        <aui:button type="submit" />
	    </aui:button-row>
	</aui:form>
</div>