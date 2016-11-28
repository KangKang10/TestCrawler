package com.douban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	// 每行作为一个Object对象
	private List<BookInfo> books;

	public WriteExcel(List<BookInfo> books) {
		this.books = books;
	}

	public Workbook getWeebWork(String filename) throws IOException {
		Workbook workbook = null;
		if (null != filename) {
			String fileType = filename.substring(filename.lastIndexOf("."), filename.length());
			FileInputStream fileStream = new FileInputStream(new File(filename));
			if (".xls".equals(fileType.trim().toLowerCase())) {
				workbook = new HSSFWorkbook(fileStream);// 创建 Excel 2003 工作簿对象
			} else if (".xlsx".equals(fileType.trim().toLowerCase())) {
				workbook = new XSSFWorkbook(fileStream);// 创建 Excel 2007 工作簿对象
			}
		}
		return workbook;
	}
	
	public void write(String path) throws Exception {
		Workbook workbook = getWeebWork(path); // 创建工作簿对象
		Sheet sheet = workbook.getSheetAt(0);
		int length=books.size();
		if(length>100){
			length=100;
		}
		for (int i = 0; i < length; i++) {
			Row row = sheet.createRow(i+1);
			
			Cell cell0 = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell0.setCellValue(i+1);
			
			Cell cell1 = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell1.setCellValue(books.get(i).getBookName());
			
			Cell cell2 = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell2.setCellValue(books.get(i).getScore());
			
			Cell cell3 = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell3.setCellValue(books.get(i).getNums());
			
			Cell cell4 = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell4.setCellValue(books.get(i).getAuthor());
			
			Cell cell5 = row.createCell(5, HSSFCell.CELL_TYPE_STRING);
			cell5.setCellValue(books.get(i).getPublishing());
			
			Cell cell6 = row.createCell(6, HSSFCell.CELL_TYPE_STRING);
			cell6.setCellValue(books.get(i).getPublishDate());
			
			Cell cell7 = row.createCell(7, HSSFCell.CELL_TYPE_STRING);
			cell7.setCellValue(books.get(i).getPrice());
		}
		
		 FileOutputStream out = new FileOutputStream(path);
         workbook.write(out);
	}

	public List<BookInfo> getBooks() {
		return books;
	}

	public void setBooks(List<BookInfo> books) {
		this.books = books;
	}
}
