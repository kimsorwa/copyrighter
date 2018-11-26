package com.kh.follow.model.vo;

import java.io.Serializable;

public class Follow implements Serializable {
	
	private int fid;
	private int followerid;
	private int followid;
	private String followername;
	private String followname;
	private String chk;
	
	public Follow() {
		super();
	}

	public Follow(int fid, int followerid, int followid, String followername, String followname) {
		super();
		this.fid = fid;
		this.followerid = followerid;
		this.followid = followid;
		this.followername = followername;
		this.followname = followname;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getFollowerid() {
		return followerid;
	}

	public void setFollowerid(int followerid) {
		this.followerid = followerid;
	}

	public int getFollowid() {
		return followid;
	}

	public void setFollowid(int followid) {
		this.followid = followid;
	}

	public String getFollowername() {
		return followername;
	}

	public void setFollowername(String followername) {
		this.followername = followername;
	}

	public String getFollowname() {
		return followname;
	}

	public void setFollowname(String followname) {
		this.followname = followname;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	@Override
	public String toString() {
		return "Follow [fid=" + fid + ", followerid=" + followerid + ", followid=" + followid + ", followername="
				+ followername + ", followname=" + followname + ", chk=" + chk + "]";
	}

}
