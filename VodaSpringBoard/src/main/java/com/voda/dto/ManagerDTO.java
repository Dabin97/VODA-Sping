package com.voda.dto;

import org.apache.ibatis.type.Alias;

@Alias("manager")
public class ManagerDTO {
	private String mid;
	private String mpasswd;
	private String mname;
	
	public ManagerDTO(String mid, String mpasswd, String mname) {
		
		this.mid = mid;
		this.mpasswd = mpasswd;
		this.mname = mname;
	}

	public ManagerDTO() {
		
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpasswd() {
		return mpasswd;
	}

	public void setMpasswd(String mpasswd) {
		this.mpasswd = mpasswd;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "ManagerDTO [mid=" + mid + ", mpasswd=" + mpasswd + ", mname=" + mname + "]";
	}
	 
	
	
}
