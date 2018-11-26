package com.kh.payment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Payment implements Serializable{

	private int pid;
	private int pmoneyid;
	private Date pdate;
	private int mid;
	private String mname;
	private String memail;
	private int pmoney;
	private int phodu;
	private int pmoney_1;
	
	public Payment() {
		super();
	}

	public Payment(int pid, int pmoneyid, Date pdate, int mid, String mname, String memail, int pmoney, int phodu,
			int pmoney_1) {
		super();
		this.pid = pid;
		this.pmoneyid = pmoneyid;
		this.pdate = pdate;
		this.mid = mid;
		this.mname = mname;
		this.memail = memail;
		this.pmoney = pmoney;
		this.phodu = phodu;
		this.pmoney_1 = pmoney_1;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPmoneyid() {
		return pmoneyid;
	}

	public void setPmoneyid(int pmoneyid) {
		this.pmoneyid = pmoneyid;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public int getPmoney() {
		return pmoney;
	}

	public void setPmoney(int pmoney) {
		this.pmoney = pmoney;
	}

	public int getPhodu() {
		return phodu;
	}

	public void setPhodu(int phodu) {
		this.phodu = phodu;
	}

	public int getPmoney_1() {
		return pmoney_1;
	}

	public void setPmoney_1(int pmoney_1) {
		this.pmoney_1 = pmoney_1;
	}

	@Override
	public String toString() {
		return "Payment [pid=" + pid + ", pmoneyid=" + pmoneyid + ", pdate=" + pdate + ", mid=" + mid + ", mname="
				+ mname + ", memail=" + memail + ", pmoney=" + pmoney + ", phodu=" + phodu + ", pmoney_1=" + pmoney_1
				+ "]";
	}
}