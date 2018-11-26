package com.kh.spon.model.vo;

import java.sql.Date;

public class Spon {

	private int sid;
	private int shodu;
	private Date sdate;
	private int sgiverid;
	private int sreceiverid;
	private String sgivername;
	private String mname;
	private String sreceivername;
	
	public String getSreceivername() {
		return sreceivername;
	}

	public void setSreceivername(String sreceivername) {
		this.sreceivername = sreceivername;
	}

	public Spon() {
		super();
	}

	public Spon(int sid, int shodu, Date sdate, int sgiverid, int sreceiverid, String sgivername, String mname) {
		super();
		this.sid = sid;
		this.shodu = shodu;
		this.sdate = sdate;
		this.sgiverid = sgiverid;
		this.sreceiverid = sreceiverid;
		this.sgivername = sgivername;
		this.mname = mname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getShodu() {
		return shodu;
	}

	public void setShodu(int shodu) {
		this.shodu = shodu;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getSgiverid() {
		return sgiverid;
	}

	public void setSgiverid(int sgiverid) {
		this.sgiverid = sgiverid;
	}

	public int getSreceiverid() {
		return sreceiverid;
	}

	public void setSreceiverid(int sreceiverid) {
		this.sreceiverid = sreceiverid;
	}

	public String getSgivername() {
		return sgivername;
	}

	public void setSgivername(String sgivername) {
		this.sgivername = sgivername;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "Spon [sid=" + sid + ", shodu=" + shodu + ", sdate=" + sdate + ", sgiverid=" + sgiverid
				+ ", sreceiverid=" + sreceiverid + ", sgivername=" + sgivername + ", mname=" + mname + "]";
	}
	
	
}