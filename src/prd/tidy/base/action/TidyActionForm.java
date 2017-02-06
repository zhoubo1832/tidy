package prd.tidy.base.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public abstract class TidyActionForm extends ActionForm{

	protected ActionMapping mapping;
	
	protected HttpServletRequest request;
		
	private ActionErrors errors = new ActionErrors();
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		this.mapping = mapping;
		this.request = request;
		
		String methodName = (String)request.getAttribute(Constants.TIDY_ACTION_METHOD_KEY);
		if (Constants.TIDY_ACTION_DEFAULT_METHOD.equals(methodName)) {
			return null;
		}
		
		validate();

		return errors;
	}
	
	protected void validate() {
		return;
	}
		
	public void addErrorMessage(String property, String key, Object[] values) {
		errors.add(property, new ActionMessage(key, values));
		
	}
	
	public void addErrorMessage(String property, String key) {
		errors.add(property, new ActionMessage(key));
		
	}
	
	public void addErrorMessage(String property, String key, boolean resource) {
		errors.add(property, new ActionMessage(key, resource));
		
	}
}
