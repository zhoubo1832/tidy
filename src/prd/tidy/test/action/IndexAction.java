package prd.tidy.test.action;

import prd.tidy.base.action.TidyAction;
import prd.tidy.test.form.JobHistoryBean;

public class IndexAction extends TidyAction{

	private String name;
	
	private int age;
	
	private JobHistoryBean jobHistory = new JobHistoryBean();
	
	private String testStr;
	
	public String init()
	        throws Exception {
		return "success";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public JobHistoryBean getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(JobHistoryBean jobHistory) {
		this.jobHistory = jobHistory;
	}

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
}
