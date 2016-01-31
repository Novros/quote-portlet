package cz.novros.lif.quotes.portlet.random;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
//import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

//import static cz.novros.lif.quotes.portlet.random.RandomQuotesConstants.*;

public class RandomQuotesPortletConfiguration extends DefaultConfigurationAction {

	@Override
	public final void processAction(final PortletConfig portletConfig, final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception {  
		super.processAction(portletConfig, actionRequest, actionResponse);
		// TODO Nothing to do here.
	    /* PortletPreferences prefs = actionRequest.getPreferences();
	    String localQuotesStr = prefs.getValue("localQuotes", "false"); */
	}
}
