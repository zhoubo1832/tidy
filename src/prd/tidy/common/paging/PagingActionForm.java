package prd.tidy.common.paging;

import prd.tidy.base.action.TidyActionForm;

public class PagingActionForm extends TidyActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int curPageNo;
	
	private int pageItemNum;

	public int getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}

	public int getPageItemNum() {
		return pageItemNum;
	}

	public void setPageItemNum(int pageItemNum) {
		this.pageItemNum = pageItemNum;
	}
	
}
