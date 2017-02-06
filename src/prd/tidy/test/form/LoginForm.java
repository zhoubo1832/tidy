package prd.tidy.test.form;

import prd.tidy.base.action.TidyActionForm;

public class LoginForm extends TidyActionForm{

	private String name;
	
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	protected void validate() {
		if ("".equals(name.trim())) {
			addErrorMessage("error", "login.user");
		}
		
		if ("".equals(password.trim())) {
			addErrorMessage("error2", "login.password");
		}
	}
}
