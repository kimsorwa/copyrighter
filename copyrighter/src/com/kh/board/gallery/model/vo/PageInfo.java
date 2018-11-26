package com.kh.board.gallery.model.vo;

import java.io.Serializable;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1000L;
	
	private int currentPage;
	private int countGalleryList;
	private int limit;
	private int maxPage;
	private int startPage;
	private int endPage;
	
	public PageInfo(){
		super();
	}
	
	public PageInfo(int currentPage, int listCount, int limit, int maxPage, int startPage, int endPage) {
		super();
		this.currentPage = currentPage;
		this.countGalleryList = listCount;
		this.limit = limit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getListCount() {
		return countGalleryList;
	}

	public void setListCount(int listCount) {
		this.countGalleryList = listCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", countGalleryList=" + countGalleryList + ", limit=" + limit
				+ ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
