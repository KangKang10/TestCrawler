package com.douban;

import java.io.Serializable;

public class BookInfo implements Serializable{
	private static final long serialVersionUID = 3120052389627848037L;
	
	/**序列号*/
	private int seq;
	
	/**书名*/
	private String bookName;
	
	/**评分*/
	private String score;
	
	/**评论数*/
	private String nums;
	
	/**作者*/
	private String author;
	
	/**出版社*/
	private String publishing;
	
	/**出版日期*/
	private String publishDate;
	
	/**价格*/
	private String price;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}