package com.voda.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("review")
public class ReviewDTO {
	private int rno;
	private String rDate;
	private String reviewContent;
	private int bno;
	private String id;
	private String starNo;
	private String title;
	private int rlike;
	private int rhate;
	
	

	public ReviewDTO() {
		super();
	}



	public ReviewDTO(int rno, String rDate, String reviewContent, int bno, String id, String starNo, String title,
			int rlike, int rhate) {
		super();
		this.rno = rno;
		this.rDate = rDate;
		this.reviewContent = reviewContent;
		this.bno = bno;
		this.id = id;
		this.starNo = starNo;
		this.title = title;
		this.rlike = rlike;
		this.rhate = rhate;
	}



	public int getRno() {
		return rno;
	}



	public void setRno(int rno) {
		this.rno = rno;
	}



	public String getrDate() {
		return rDate;
	}



	public void setrDate(String rDate) {
		this.rDate = rDate;
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



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getRlike() {
		return rlike;
	}



	public void setRlike(int rlike) {
		this.rlike = rlike;
	}



	public int getRhate() {
		return rhate;
	}



	public void setRhate(int rhate) {
		this.rhate = rhate;
	}



	@Override
	public String toString() {
		return "ReviewDTO [rno=" + rno + ", rDate=" + rDate + ", reviewContent=" + reviewContent + ", bno=" + bno
				+ ", id=" + id + ", starNo=" + starNo + ", title=" + title + ", rlike=" + rlike + ", rhate=" + rhate
				+ "]";
	}



	}