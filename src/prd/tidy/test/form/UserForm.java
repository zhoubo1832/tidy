package prd.tidy.test.form;

import prd.tidy.base.action.TidyActionForm;

public class UserForm extends TidyActionForm{

	private String name;
	
	private String password;
	
	private int age;
	
	private JobHistoryBean jobHistory;

	public UserForm() {
		jobHistory = new JobHistoryBean();
	}
	
	
	
	
	@Override
	public void validate() {
	    if ("".equals(name) || "".equals(password)) {
	    	addErrorMessage("inputerror","Input error!",false);
	    }
	}




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
	
	
}
