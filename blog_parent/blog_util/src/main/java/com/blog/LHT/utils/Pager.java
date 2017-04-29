package com.blog.LHT.utils;

import java.util.List;

public class Pager<T> {

	private List<T>currentRecord;
	private Long recordCount;
	private Long pageCount;
	private int currentPage;
	private int pageSize;
	private int beginPosition;
	public int getBeginPosition() {
		return beginPosition;
	}
	public void setBeginPosition(int beginPosition) {
		this.beginPosition = beginPosition;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getCurrentRecord() {
		return currentRecord;
	}
	public void setCurrentRecord(List<T> currentRecord) {
		this.currentRecord = currentRecord;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
