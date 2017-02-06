package prd.tidy.base.action;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class TidyAction extends Action{
	protected ActionMapping mapping;
	protected ActionForm form;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	private ActionMessages messages = new ActionMessages();
	
	private ActionErrors errors = new ActionErrors();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		this.mapping = mapping;
		this.form = form;
		this.request = request;
		this.response = response;
		
		if (form != null) {
			HashMap properties = new HashMap();
			Field[] fields = form.getClass().getDeclaredFields();
			for (Field f : fields) {
				String n = f.getName();
				String upperN = n.substring(0,1).toUpperCase();
				
				try{
					Method m = form.getClass().getMethod("get" + upperN + n.substring(1),new Class[]{});
					properties.put(f.getName(), m.invoke(form,new Object[]{}));
				} catch (Exception e) {
					
				}
				
			}
			BeanUtils.populate(this, properties);
		} else {
			BeanUtils.populate(this, request.getParameterMap());
		}
		
		String methodName = (String)request.getAttribute(Constants.TIDY_ACTION_METHOD_KEY);
				
		if (methodName != null && methodName.length() > 0) {
			Method method = this.getClass().getMethod(methodName, new Class[]{});
			if (method != null) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation a : annotations) {
					if (a.annotationType().getName().equals(SaveToken.class.getName())) {
						this.saveToken(request);
					} else if (a.annotationType().getName().equals(CheckToken.class.getName())) {
						if (!isTokenValid(request, true)) {
							String failure = method.getAnnotation(CheckToken.class).failure();
							return mapping.findForward(failure);
						}
					}
				}
				String forward = (String)method.invoke(this, new Object[]{});
				
				Field[] fields = this.getClass().getDeclaredFields();
				for (Field f : fields) {
					SaveToScope scope = f.getAnnotation(SaveToScope.class);
					if (scope != null) {
						
						Object obj = f.get(this);
						if (obj != null) {
							Scope s = scope.scope();
							if (s == Scope.REQUEST) {
								request.setAttribute(f.getName(), obj);
							} else if (s == Scope.SESSION) {
								request.getSession().setAttribute(f.getName(), obj);
							} else if (s == Scope.APPLICATION) {
								this.getServlet().getServletContext().setAttribute(f.getName(), obj);
							}
						}
					}
				}
				
				this.addMessages(request, messages);
				
				this.addErrors(request, errors);
				
				return mapping.findForward(forward);
			}
		}
		
		return null;
	}
	
	public void addMessage(String property, String key, Object[] values) {
		messages.add(property, new ActionMessage(key, values));
		
	}
	
	public void addMessage(String property, String key) {
		messages.add(property, new ActionMessage(key));
		
	}
	
	public void addMessage(String property, String key, boolean resource) {
		messages.add(property, new ActionMessage(key, resource));
		
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
