package prd.tidy.base.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.chain.commands.servlet.SelectAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;

public class TidySelectAction extends SelectAction{
	
	protected String getPath(ActionContext context) {
		String path = super.getPath(context);
		
		ServletActionContext saContext = (ServletActionContext) context;
        HttpServletRequest request = saContext.getRequest();
        
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
}
