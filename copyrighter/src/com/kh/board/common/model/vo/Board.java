package com.kh.board.common.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
	
	private static final long serialVersionUID = 90999L;
	
	private int bid; 
	private int btype;
	private String btitle;
	private String bcontent;
	private int bcount;
	private Date bdate;
	private String bstatus;
	private int bwriter;
	private int brcount;
	private String mprofile;
	private String mname;
	
	private String fName;
	private String jTag;
	private String gTag;
	
	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getCommCnt() {
		return commCnt;
	}

	public void setCommCnt(int commCnt) {
		this.commCnt = commCnt;
	}

	private int likeCnt;
	private int commCnt;
	
	public String getjTag() {
		return jTag;
	}

	public void setjTag(String jTag) {
		this.jTag = jTag;
	}

	public String getgTag() {
		return gTag;
	}

	public void setgTag(String gTag) {
		this.gTag = gTag;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fname) {
		this.fName = fname;
	}

	public Board() {}
	
	public Board(int bid, int btype, String btitle, String bcontent, int bcount, Date bdate, String bstatus,
			int bwriter, int brcount) {
		super();

		this.bid = bid;					// 게시글 번호
		this.btype = btype;				// 게시글 타입	
		this.btitle = btitle;			// 제목
		this.bcontent = bcontent;		// 내용
		this.bcount = bcount;			// 조회수
		this.bdate = bdate;				// 작성일
		this.bstatus = bstatus;			// 삭제여부
		this.bwriter = bwriter;			// 작성자(회원번호)
		this.brcount = brcount;			// 신고횟수

	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getBstatus() {
		return bstatus;
	}

	public void setBstatus(String bstatus) {
		this.bstatus = bstatus;
	}

	public int getBwriter() {
		return bwriter;
	}

	public void setBwriter(int bwriter) {
		this.bwriter = bwriter;
	}

	public int getBrcount() {
		return brcount;
	}

	public void setBrcount(int brcount) {
		this.brcount = brcount;
	}

	public String getMprofile() {
		return mprofile;
	}

	public void setMprofile(String mprofile) {
		this.mprofile = mprofile;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", btype=" + btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bcount="
				+ bcount + ", bdate=" + bdate + ", bstatus=" + bstatus + ", bwriter=" + bwriter + ", brcount=" + brcount
				+ ", mprofile=" + mprofile + ", mname=" + mname + "]";
	}
	
}

