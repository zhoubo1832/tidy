package prd.tidy.common.paging;

import java.util.List;

import prd.tidy.test.form.ResultBean;

public class PagingBean {

	private int curPageNo = 1;
	
	private int pageItemNum = 20;
	
	private int totalItemNum;
	
	private int pageNum;
	
	private int begin;
	
	private int end;
	
	private List<ResultBean> listResult;

	public PagingBean() {
		
	}
	
	public PagingBean(int curPageNo, int pageItemNum, int totalItemNum) {
		this.curPageNo = curPageNo;
		this.pageItemNum = pageItemNum;
		this.totalItemNum = totalItemNum;
	}
	
	public List<ResultBean> getListResult() {
		return listResult;
	}

	public void setListResult(List<ResultBean> listResult) {
		this.listResult = listResult;
	}

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

	public int getTotalItemNum() {
		return totalItemNum;
	}

	public void setTotalItemNum(int totalItemNum) {
		this.totalItemNum = totalItemNum;
		
		computePageNum();
		
		int h = curPageNo/10;
		int t = curPageNo%10;
		if (h == 0) {
			this.setBegin(1);
		} else if (t == 0) {
			this.setBegin(curPageNo-9);
		} else {
			this.setBegin(h*10+1);
		}
		
		int l = ((begin + 9) <= pageNum)? (begin + 9) : pageNum;
		this.setEnd(l);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	private void computePageNum() {
		if (totalItemNum == 0) {
			setPageNum(0);
			return;
		}
		int i = totalItemNum/pageItemNum;
		int j = totalItemNum%pageItemNum;
		if (j == 0) {
			setPageNum(i);
		} else {
			setPageNum(i+1);
		}
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
