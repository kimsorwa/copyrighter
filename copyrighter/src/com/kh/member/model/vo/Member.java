package com.kh.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private int Mid;
	private String Mprofile;
	private String Memail;
	private String Mpwd;
	private String Mname;
	private Date Mdate;
	private int Mhodu;
	private int Msid;
	private String Mstatus;
	
	public Member() {
		super();
	}
	
	public Member(int mid, String mprofile, String memail, String mpwd, String mname, Date mdate, int mhodu, int msid) {
		super();
		Mid = mid;
		Mprofile = mprofile;
		Memail = memail;
		Mpwd = mpwd;
		Mname = mname;
		Mdate = mdate;
		Mhodu = mhodu;
		Msid = msid;
	}
	
	public Member(String memail, String mpwd, String mname) {
		super();
		Memail = memail;
		Mpwd = mpwd;
		Mname = mname;
	}
	
	public Member(String memail, String mpwd) {
		super();
		Memail = memail;
		Mpwd = mpwd;
	}

	public int getMid() {
		return Mid;
	}
	public void setMid(int mid) {
		Mid = mid;
	}
	public String getMprofile() {
		return Mprofile;
	}
	public void setMprofile(String mprofile) {
		Mprofile = mprofile;
	}
	public String getMemail() {
		return Memail;
	}
	public void setMemail(String memail) {
		Memail = memail;
	}
	public String getMpwd() {
		return Mpwd;
	}
	public void setMpwd(String mpwd) {
		Mpwd = mpwd;
	}
	public String getMname() {
		return Mname;
	}
	public void setMname(String mname) {
		Mname = mname;
	}
	public Date getMdate() {
		return Mdate;
	}
	public void setMdate(Date mdate) {
		Mdate = mdate;
	}
	public int getMhodu() {
		return Mhodu;
	}
	public void setMhodu(int mhodu) {
		Mhodu = mhodu;
	}
	public int getMsid() {
		return Msid;
	}
	public void setMsid(int msid) {
		Msid = msid;
	}
	
	public String getMstatus() {
		return Mstatus;
	}

	public void setMstatus(String mstatus) {
		Mstatus = mstatus;
	}

	@Override
	public String toString() {
		return "Member [Mid=" + Mid + ", Mprofile=" + Mprofile + ", Memail=" + Memail + ", Mpwd=" + Mpwd + ", Mname="
				+ Mname + ", Mdate=" + Mdate + ", Mhodu=" + Mhodu + ", Msid=" + Msid + "]";
	}
		
}
