package com.douban;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BookExcelTest {

	@Test
	public void writeExcel() {
		List<BookInfo> infos = new ArrayList<>();
		BookInfo b1 = new BookInfo();
		b1.setSeq(1);
		b1.setBookName("Test1");
		b1.setScore("1.0");
		b1.setNums("20");
		b1.setAuthor("Test.Auhot");
		b1.setPublishing("shagnhan");
		b1.setPublishDate("1900-12��");
		b1.setPrice("23.00Yuanm");
		infos.add(b1);
		
		BookInfo b2 = new BookInfo();
		b2.setSeq(2);
		b2.setBookName("Test2");
		b2.setScore("2.0");
		b2.setNums("40");
		b2.setAuthor("Test1.Auhot");
		b2.setPublishing("shagnhan");
		b2.setPublishDate("1930-12��");
		b2.setPrice("25.00Yuanm");
		infos.add(b2);
		
		String path = "E:\\template.xlsx";
		try {
			new WriteExcel(infos).write(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
