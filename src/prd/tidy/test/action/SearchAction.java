package prd.tidy.test.action;

import java.util.ArrayList;
import java.util.List;

import prd.tidy.base.action.SaveToScope;
import prd.tidy.base.action.TidyAction;
import prd.tidy.common.paging.PagingBean;
import prd.tidy.test.form.ResultBean;
import prd.tidy.test.form.SearchForm;
import prd.tidy.test.form.SearchResultBean;

public class SearchAction extends TidyAction{

	@SaveToScope
	public PagingBean pagingBean = new PagingBean();
	
	public String init() {
		SearchForm searchForm = (SearchForm) this.form;
		System.out.println(searchForm.getCurPageNo());
		System.out.println(searchForm.getPageItemNum());
		
		
		
		pagingBean.setCurPageNo(searchForm.getCurPageNo());
		pagingBean.setPageItemNum(searchForm.getPageItemNum());
		
		doSearch();
		return "init";
	}

	public String search() {
		doSearch();
		
		return "success";
	}

	/**
	 * 
	 */
	private void doSearch() {
		List<ResultBean> listResult = new ArrayList<ResultBean>();
		
		listResult.add(new SearchResultBean("001", "John1", 21));
		listResult.add(new SearchResultBean("002", "John2", 22));
		listResult.add(new SearchResultBean("003", "John3", 23));
		listResult.add(new SearchResultBean("004", "John4", 24));
		listResult.add(new SearchResultBean("005", "John5", 25));
		listResult.add(new SearchResultBean("006", "John6", 26));
		listResult.add(new SearchResultBean("007", "John7", 27));
		listResult.add(new SearchResultBean("008", "John8", 28));
		listResult.add(new SearchResultBean("009", "John9", 29));
		listResult.add(new SearchResultBean("010", "John10", 30));
		listResult.add(new SearchResultBean("011", "John11", 31));
		listResult.add(new SearchResultBean("012", "John12", 32));
		
		pagingBean.setListResult(listResult);
		pagingBean.setTotalItemNum(50);
	}
	
	
	
}
