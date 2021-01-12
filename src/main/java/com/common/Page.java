package com.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @ClassName:Page.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: Page.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@SuppressWarnings("serial")
public class Page implements Serializable {
	public static final Page EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 0, 0, 0);

	@SuppressWarnings("rawtypes")
	private List objects;

	private int pageSize;

	private int totalNumber;

	private int totalPage;

	private int currentPage;
	
	private int viewCountPages;
	
	public Page(){}

	@SuppressWarnings({ "rawtypes" })
	public Page(List objects, int pageSize, int totalNum, int currentPage) {
		if (objects.equals(Collections.EMPTY_LIST)) {
			this.objects = objects;
		}else{
			this.objects = objects;
			this.totalNumber = totalNum;
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.totalPage = (totalNumber - 1) / pageSize + 1;
			if((this.currentPage+5<10)&&(this.totalPage<10)){
				this.viewCountPages = totalPage;
			}else if((this.currentPage+5<10)&&(this.totalPage>=10)){
				this.viewCountPages = 10;
			}else if(this.currentPage+5>this.totalPage){
				this.viewCountPages = this.totalPage;
			}else{
				this.viewCountPages = this.currentPage+5;
			}
		}
	}

	
	@SuppressWarnings("rawtypes")
	public List getList() {
		return objects;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public int getTotalPage() {
		return totalPage;
	}


	public int getCurrentPage() {
		if(this.getTotalNumber()>0)
			currentPage = currentPage<1?1:currentPage;
		return currentPage;
	}


	@SuppressWarnings("rawtypes")
	public void setList(List objects) {
		this.objects = objects;
	}
	
	public String toString(){
		StringBuffer sb=null;
		if(objects!=null){
			sb=new StringBuffer(500);
			for(int i=0;i<objects.size();i++){
				sb.append(objects.toString()).append(",");
			}
		}
		return sb==null?"":sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public List getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	@SuppressWarnings("rawtypes")
	public void setObjects(List objects) {
		this.objects = objects;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param totalNumber the totalNumber to set
	 */
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getViewCountPages() {
		return viewCountPages;
	}

	public void setViewCountPages(int viewCountPages) {
		this.viewCountPages = viewCountPages;
	}

	
	
}
