package prd.tidy.test.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts.upload.FormFile;

import prd.tidy.base.action.TidyAction;
import prd.tidy.test.form.FileUploadForm;

public class FileUploadAction extends TidyAction{

	private String desc;
	
	private List<FormFile> myFiles;
	
	private String uploadPath;
	
	public String init() {
		return "init";
	}
	
	public String upload() {
		uploadPath = this.getServlet().getInitParameter("uploadpath");
		
		System.out.println("request charset:" + request.getCharacterEncoding());
		System.out.println("request content-type:" + request.getContentType());
		
		System.out.println("File description:" + desc);
		int fileNum = myFiles.size();
		for (int i=0; i<fileNum; i++) {
			saveFile(myFiles.get(i));
		}
		
		return "success";
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<FormFile> getMyFiles() {
		return myFiles;
	}

	public void setMyFiles(List<FormFile> myFiles) {
		this.myFiles = myFiles;
	}
	
	private void saveFile(FormFile ff) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = ff.getInputStream();
			
			byte[] buffer = new byte[1024];
			int readNum = 0;
			
			outputStream = new FileOutputStream(uploadPath + "/" + ff.getFileName());
			
			while ((readNum = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer,0,readNum);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try{
				if (inputStream != null) {
					inputStream.close();
				}
				
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				
			}
		}
	}
}
