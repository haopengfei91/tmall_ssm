package com.faymax.util;

public class Page {
	
	//每页起始位置
	private int start;
	//每页显示个数
	private int count;
	//总个数
	private int total;
	private String param;
	
	private static final int defaultCount = 5;
	
	public Page() {
		count = defaultCount;
	}

	public Page(int start, int count) {
		this();
		this.start = start;
		this.count = count;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public boolean isFirstPage() {
		return start==0?true:false;
	}
	
	public boolean isLastPage() {
		return start==getLast()?true:false;
	}

	public int getLast() {
		int last;
		if(total%count>0) {
			last = total - total%count;
		} else {
			last = total - count;
		}
		return last>0?last:0;
	}
	
	public int getTotalPage() {
		int totalPage;
		if(total%count>0) {
			totalPage = total/count + 1;
		} else {
			totalPage = total/count;
		}
		totalPage = totalPage>0?totalPage:1;
		return totalPage;
	}
	
	@Override
	public String toString() {
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
        + ", getCount()=" + getCount() + ", isFirstPage()=" + isFirstPage() + ", isLastPage()="
        + isLastPage() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
	}
}
