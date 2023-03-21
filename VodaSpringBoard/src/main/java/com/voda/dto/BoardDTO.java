package com.voda.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("board")
public class BoardDTO {
	private int bno; 
	private String title;
	private String pd;
	private String casting; 
	private String content;
	private String poster; //poster를 따로 넣을지 그냥 board_image 테이블을 따로 만들었으니 뺄지 결정할것
	private Date new_expire;
	private Date bdate;
	private int genre;
	private String post_video; //따로 테이블로 뺼지 고민
	private int ott_no;
	
	public BoardDTO() {	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPd() {
		return pd;
	}

	public void setPd(String pd) {
		this.pd = pd;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getNew_expire() {
		return new_expire;
	}

	public void setNew_expire(Date new_expire) {
		this.new_expire = new_expire;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public String getPost_video() {
		return post_video;
	}

	public void setPost_video(String post_video) {
		this.post_video = post_video;
	}

	public int getOtt_no() {
		return ott_no;
	}

	public void setOtt_no(int ott_no) {
		this.ott_no = ott_no;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", pd=" + pd + ", casting=" + casting + ", content="
				+ content + ", poster=" + poster + ", new_expire=" + new_expire + ", bdate=" + bdate + ", genre="
				+ genre + ", post_video=" + post_video + ", ott_no=" + ott_no + "]";
	}
	
	
}
