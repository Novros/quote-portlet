package cz.novros.lif.quotes.portlet.random;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

public class RandomQuotesPortletConfiguration extends DefaultConfigurationAction {

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {  

		super.processAction(portletConfig, actionRequest, actionResponse);

	    PortletPreferences prefs = actionRequest.getPreferences();
	    
	    String localQuotes = prefs.getValue("localQuotes", "false");
        System.out.println(localQuotes);
        
	}
}
