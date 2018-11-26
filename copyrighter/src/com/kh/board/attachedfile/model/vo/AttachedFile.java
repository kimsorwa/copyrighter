package com.kh.board.attachedfile.model.vo;


import com.kh.board.common.model.vo.Board;


import java.io.Serializable;

public class AttachedFile extends Board implements Serializable{

	
	private int fid;
	private String fname;
	private String fpath;
	private int flevel;
	private int bid;
	
	public AttachedFile() {
		super();
	}

	public AttachedFile(int fid, String fname, String fpath, int flevel, int bid) {
		super();
		this.fid = fid;				// 파일번호
		this.fname = fname;			// 파일이름
		this.fpath = fpath;			// 파일경로
		this.flevel = flevel;		// 파일레벨
		this.bid = bid;				// 게시글번호
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public int getFlevel() {
		return flevel;
	}

	public void setFlevel(int flevel) {
		this.flevel = flevel;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "AttachedFile [fid=" + fid + ", fname=" + fname + ", fpath=" + fpath + ", flevel=" + flevel + ", bid="
				+ bid + "]";
	}


}
