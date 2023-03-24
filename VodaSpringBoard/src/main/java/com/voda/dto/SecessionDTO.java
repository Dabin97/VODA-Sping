package com.voda.dto;

import org.apache.ibatis.type.Alias;

@Alias("secession")
public class SecessionDTO {
	private String id;
	private String reason;
	private String sdate; //
	private int sno;
	
	public SecessionDTO(String id, String reason, String sdate, int sno) {
		this.id = id;
		this.reason = reason;
		this.sdate = sdate;
		this.sno = sno;
	}

	public SecessionDTO() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	@Override
	public String toString() {
		return "SecessionDTO [id=" + id + ", reason=" + reason + ", sdate=" + sdate + ", sno=" + sno + "]";
	}
	
	
}
