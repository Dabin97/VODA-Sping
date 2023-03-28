package com.voda.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("review")
public class ReviewDTO {
	private int rno;
	private Date rdate;
	private String reviewContent;
	private int bno;
	private String id;
	private String starNo;
	
	public ReviewDTO() {
		
	}

	public ReviewDTO(int rno, Date rdate, String reviewContent, int bno, String id, String starNo) {
		super();
		this.rno = rno;
		this.rdate = rdate;
		this.reviewContent = reviewContent;
		this.bno = bno;
		this.id = id;
		this.starNo = starNo;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStarNo() {
		return starNo;
	}

	public void setStarNo(String starNo) {
		this.starNo = starNo;
	}

	@Override
	public String toString() {
		return "ReviewDTO [rno=" + rno + ", rdate=" + rdate + ", reviewContent=" + reviewContent + ", bno=" + bno
				+ ", id=" + id + ", starNo=" + starNo + "]";
	}
	
	

}