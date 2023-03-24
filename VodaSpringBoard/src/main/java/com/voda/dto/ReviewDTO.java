package com.voda.dto;

import org.apache.ibatis.type.Alias;

@Alias("review")
public class ReviewDTO {
	private int rno;
	private int bno;
	private String rdate;
	private String review_content;
	private String id;
	private int star_no;
	private int rating;
	
	
	public ReviewDTO() {
	}


	public ReviewDTO(int rno, int bno, String rdate, String review_content, String id, int star_no, int rating) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.rdate = rdate;
		this.review_content = review_content;
		this.id = id;
		this.star_no = star_no;
		this.rating = rating;
	}


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getRdate() {
		return rdate;
	}


	public void setRdate(String rdate) {
		this.rdate = rdate;
	}


	public String getReview_content() {
		return review_content;
	}


	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getStar_no() {
		return star_no;
	}


	public void setStar_no(int star_no) {
		this.star_no = star_no;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	@Override
	public String toString() {
		return "ReviewDTO [rno=" + rno + ", bno=" + bno + ", rdate=" + rdate + ", review_content=" + review_content
				+ ", id=" + id + ", star_no=" + star_no + ", rating=" + rating + "]";
	}
	
	
	
	

}
