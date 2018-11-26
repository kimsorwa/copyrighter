package com.kh.alarm.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.member.model.vo.Member;

public class Alarm extends Member implements Serializable {

	private static final long serialVersionUID = 1000L;
	
	private int Aid;
	private int Mid;
	private String AMsg;
	private Date ADate;
	private String AFlag;
	
	public Alarm() {
		super();
	}

	public Alarm(int aid, int mid, String aMsg, Date aDate, String aFlag) {
		super();
		Aid = aid;
		Mid = mid;
		AMsg = aMsg;
		ADate = aDate;
		AFlag = aFlag;
	}

	public int getAid() {
		return Aid;
	}

	public void setAid(int aid) {
		Aid = aid;
	}

	public int getMid() {
		return Mid;
	}

	public void setMid(int mid) {
		Mid = mid;
	}

	public String getAMsg() {
		return AMsg;
	}

	public void setAMsg(String aMsg) {
		AMsg = aMsg;
	}

	public Date getADate() {
		return ADate;
	}

	public void setADate(Date aDate) {
		ADate = aDate;
	}

	public String getAFlag() {
		return AFlag;
	}

	public void setAFlag(String aFlag) {
		AFlag = aFlag;
	}

	@Override
	public String toString() {
		return "Alarm [Aid=" + Aid + ", Mid=" + Mid + ", AMsg=" + AMsg + ", ADate=" + ADate + ", AFlag=" + AFlag + "]";
	}
	
	
}
