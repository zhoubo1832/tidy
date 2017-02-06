package prd.tidy.test.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import prd.tidy.base.action.TidyActionForm;

public class FileUploadForm extends TidyActionForm{

	private String desc;
	
	private List<FormFile> myFiles = new ArrayList<FormFile>();

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public FormFile getFile(int i) {
        return myFiles.get(i);
    }

    public void setFile(int i,FormFile file) {
    	myFiles.add(file);
    }
    
	public List<FormFile> getMyFiles() {
		return myFiles;
	}

	public void setMyFiles(List<FormFile> myFiles) {
		this.myFiles = myFiles;
	}
}
