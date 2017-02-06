package prd.tidy.test.action;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import prd.tidy.base.action.CheckToken;
import prd.tidy.base.action.Scope;
import prd.tidy.base.action.SaveToScope;
import prd.tidy.base.action.SaveToken;
import prd.tidy.base.action.TidyAction;
import prd.tidy.test.form.JobHistoryBean;
import prd.tidy.test.service.LoginService;

public class LoginAction extends TidyAction{

	private String name;
	
	private String password;
	
	private int age;
	
	private LoginService loginService;
	
	@SaveToScope(scope=Scope.SESSION)
	public JobHistoryBean jobHistory;
	 
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SaveToken
	public String init() {
		
		System.out.println(mapping.getAttribute());
		System.out.println("request parameter name:" + this.request.getParameter("name"));
		System.out.println("request parameter age:" + this.request.getParameter("age"));
		
		System.out.println("name:" + name);
		System.out.println("age:" + age);
		//System.out.println(jobHistory.getCompanyName() + "-" + jobHistory.getDuration());
		System.out.println(this);
		

		addMessage("testMsg", "tidy.test.message");
		addMessage("testMsg", "tidy.welcome.message", new Object[]{"Sunny"});
		addMessage("UiMsg", "login.user.name");
		addMessage("UiMsg", "hhha!!!", false);
		
		System.out.println("referer:" + request.getHeader("referer"));
		return "jsppage";
	}

	@CheckToken(failure="error")
	public String login() {
		jobHistory = new JobHistoryBean();
		jobHistory.setCompanyName("imb");
		jobHistory.setDuration(21);
		
		
		//ServletContext context = this.getServlet().getServletContext();
		
		//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
		
//		WebApplicationContext wac = (WebApplicationContext)context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//LoginService loginService = (LoginService)wac.getBean("loginService");
		System.out.println(loginService);
		loginService.login();
		System.out.println("referer:" + request.getHeader("referer"));
		return "success";
	}
	
	public JobHistoryBean getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(JobHistoryBean jobHistory) {
		this.jobHistory = jobHistory;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
