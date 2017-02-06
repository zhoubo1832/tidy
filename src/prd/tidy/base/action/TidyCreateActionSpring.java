package prd.tidy.base.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.servlet.CreateAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.DelegatingActionUtils;

public class TidyCreateActionSpring extends CreateAction{

	protected synchronized Action getAction(ActionContext context, String type,
        ActionConfig actionConfig)
        throws Exception {
        
        Action action = null;
        
        ServletActionContext saContext = (ServletActionContext) context;
        ActionServlet actionServlet = saContext.getActionServlet();
        ModuleConfig moduleConfig = saContext.getModuleConfig();
        
        String prefix = moduleConfig.getPrefix();
        String path = context.getActionConfig().getPath();
        
        WebApplicationContext  wac = DelegatingActionUtils.findRequiredWebApplicationContext(actionServlet, moduleConfig);
        if (wac.containsBean(prefix+path)) {
        	return (Action)wac.getBean(prefix+path, Action.class);
        }
        
        action = createAction(context, type);
               
        if (action.getServlet() == null) {
            action.setServlet(actionServlet);
        }

        return (action);
    }
}
