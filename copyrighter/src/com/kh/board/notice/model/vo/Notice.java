package com.kh.board.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

import com.kh.board.common.model.vo.Board;

public class Notice extends Board implements Serializable {

	public Notice() {
		super();
	}

	public Notice(int bid, int btype, String btitle, String bcontent, int bcount, Date bdate, String bstatus,
			int bwriter, int brcount) {
		super(bid, btype, btitle, bcontent, bcount, bdate, bstatus, bwriter, brcount);
	}

	@Override
	public String toString() {
		return "Notice [getBid()=" + getBid() + ", getBtype()=" + getBtype() + ", getBtitle()=" + getBtitle()
				+ ", getBcontent()=" + getBcontent() + ", getBcount()=" + getBcount() + ", getBdate()=" + getBdate()
				+ ", getBstatus()=" + getBstatus() + ", getBwriter()=" + getBwriter() + ", getBrcount()=" + getBrcount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
		
}
