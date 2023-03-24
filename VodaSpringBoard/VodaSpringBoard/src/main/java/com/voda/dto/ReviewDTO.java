package com.voda.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("review")
public class ReviewDTO {
	private int rno;
	private Date rdate;
	private String review_content;
	private int bno;
	private String id;
	private String star_no;
	
	public ReviewDTO() {
		
	}

	public ReviewDTO(int rno, Date rdate, String review_content, int bno, String id, String star_no) {
		super();
		this.rno = rno;
		this.rdate = rdate;
		this.review_content = review_content;
		this.bno = bno;
		this.id = id;
		this.star_no = star_no;
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

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
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

	public String getStar_no() {
		return star_no;
	}

	public void setStar_no(String star_no) {
		this.star_no = star_no;
	}

	@Override
	public String toString() {
		return "ReviewDTO [rno=" + rno + ", rdate=" + rdate + ", review_content=" + review_content + ", bno=" + bno
				+ ", id=" + id + ", star_no=" + star_no + "]";
	}
	
	
}
