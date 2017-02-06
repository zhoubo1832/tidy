package prd.tidy.base.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.servlet.CreateAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;

public class TidyCreateAction extends CreateAction{

	protected synchronized Action getAction(ActionContext context, String type,
        ActionConfig actionConfig)
        throws Exception {
        
        Action action = null;

        action = createAction(context, type);
               
        if (action.getServlet() == null) {
            ServletActionContext saContext = (ServletActionContext) context;
            ActionServlet actionServlet = saContext.getActionServlet();

            action.setServlet(actionServlet);
        }

        return (action);
    }
}
