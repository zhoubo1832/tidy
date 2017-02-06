package prd.tidy.base.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.util.RequestUtils;

public class TidyRequestProcessor extends RequestProcessor{

	protected String processPath(HttpServletRequest request,
	        HttpServletResponse response)
	        throws IOException {
		String path = super.processPath(request, response);

		int slash = path.lastIndexOf("/");
		if (slash > 0) {
			String methodName = path.substring(slash+1);
			if (methodName == null || "".equals(methodName.trim())) {
				methodName = Constants.TIDY_ACTION_DEFAULT_METHOD;
			}
			request.setAttribute(Constants.TIDY_ACTION_METHOD_KEY, methodName);
			return path.substring(0, slash);
		}
		request.setAttribute(Constants.TIDY_ACTION_METHOD_KEY, Constants.TIDY_ACTION_DEFAULT_METHOD);
		return path;
	}
	
	
	protected Action processActionCreate(HttpServletRequest request,
	        HttpServletResponse response, ActionMapping mapping)
	        throws IOException {
        // Acquire the Action instance we will be using (if there is one)
        String className = mapping.getType();

        // If there were a mapping property indicating whether
        // an Action were a singleton or not ([true]),
        // could we just instantiate and return a new instance here?
        Action instance;

        // Create and return a new Action instance
        if (log.isTraceEnabled()) {
            log.trace("  Creating new Action instance");
        }

        try {
            instance = (Action) RequestUtils.applicationInstance(className);

            // Maybe we should propagate this exception
            // instead of returning null.
        } catch (Exception e) {
            log.error(getInternal().getMessage("actionCreate",
                    mapping.getPath()), e);

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                getInternal().getMessage("actionCreate", mapping.getPath()));

            return (null);
        }

        if (instance.getServlet() == null) {
            instance.setServlet(this.servlet);
        }

        return (instance);
    }
}
