package prd.tidy.base.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DelegatingRequestProcessor;

public class TidyRequestProcessorSpring extends DelegatingRequestProcessor{

	public static final String TIDY_ACTION_METHOD_KEY =
        "prd.tidy.base.action.method";
	
	public static final String TIDY_ACTION_DEFAULT_METHOD =
		"init";
	
	protected String processPath(HttpServletRequest request,
	        HttpServletResponse response)
	        throws IOException {
		String path = super.processPath(request, response);

		int slash = path.lastIndexOf("/");
		if (slash > 0) {
			String methodName = path.substring(slash+1);
			if (methodName == null || "".equals(methodName.trim())) {
				methodName = TIDY_ACTION_DEFAULT_METHOD;
			}
			request.setAttribute(TIDY_ACTION_METHOD_KEY, methodName);
			return path.substring(0, slash);
		}
		request.setAttribute(TIDY_ACTION_METHOD_KEY, TIDY_ACTION_DEFAULT_METHOD);
		return path;
	}

	protected Action processActionCreate(
			HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
			throws IOException {
		Action action = super.processActionCreate(request, response, mapping);
		
		if (action.getServlet() == null) {
			action.setServlet(this.servlet);
		}
		return action;
	}
}
