package com.douban;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 写到Excel线程
 * @author kangweici
 * @date2016年11月27日下午11:07:25
 */
public class BookExcelRunnable implements Runnable {
	private List<BookInfo> books;
	public BookExcelRunnable(){
	}
	
	public BookExcelRunnable(List<BookInfo> books){
		this.books = books;
	}

	//线程入口
	@Override
	public void run() {
		String path = "E:\\template.xlsx";
		try {
			Collections.sort(books, new Comparator<BookInfo>(){
				@Override
				public int compare(BookInfo o1, BookInfo o2) {
					return Double.valueOf(o1.getScore()).doubleValue()<=Double.valueOf(o2.getScore()).doubleValue()?1:-1;
				}
				
			});
			new WriteExcel(books).write(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BookInfo> getBooks() {
		return books;
	}

	public void setBooks(List<BookInfo> books) {
		this.books = books;
	}
}
