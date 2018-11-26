package com.kh.likeit.model.vo;

import java.io.Serializable;

public class Likeit implements Serializable {
	
	private int mid;
	private int bid;
	private String bwriter;
	private String btitle;
	private String fname;
	private String mname;
		
	public Likeit() {
		super();
	}

	public Likeit(int mid, int bid, String bwriter, String btitle, String fname, String mname) {
		super();
		this.mid = mid;
		this.bid = bid;
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.fname = fname;
		this.mname= mname;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getMname() {
		return mname;
	}
	
	public void setMname(String mname){
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "Likeit [mid=" + mid + ", bid=" + bid + ", bwriter=" + bwriter + ", btitle=" + btitle + ", fname="
				+ fname + ", mname=" + mname + "]";
	}

	
}
