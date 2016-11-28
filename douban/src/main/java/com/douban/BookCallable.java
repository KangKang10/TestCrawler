package com.douban;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 解析HTML文件，获取书籍相关内容
 * @author kangweici
 * @date2016年11月27日下午11:06:11
 */
public class BookCallable implements Callable<List<BookInfo>>{
	private Document doc;
	private int page;
	
	public BookCallable(){
	}
	
	public BookCallable(Document doc, int page){
		this.doc  = doc;
		this.page = page;
	}

	@Override
	public List<BookInfo> call() throws Exception {
		List<BookInfo> infos = new ArrayList<>();
		Elements elements = doc.getElementsByClass("subject-item");
		for(int i=0; i<elements.size(); i++) {
			Element titleElement = elements.get(i).select("h2 a").first();
			Element authorElement = elements.get(i).select("div.pub").first();
			Element rateNumsElement = elements.get(i).select("span.rating_nums").first();
			Element p1Element = elements.get(i).select("span.pl").first();
			
			String title = titleElement.text();
			String author = authorElement.text();
			String nums   = rateNumsElement.text();
			String pl	  = p1Element.text();
			int plInt = Integer.parseInt(StringUtils.substringBetween(pl, "(", "人评价"));
			if(plInt>1000){
				BookInfo info = create(title, author, nums, pl, page, i);
				infos.add(info);
			}
		}
		
		return infos;
	}
	
	/**
	 * 保存到BookInfo实体类
	 * @author kangweici
	 * @date2016年11月27日下午11:06:49
	 */
	protected BookInfo create(String title, String author, String nums, String pl, int page, int i) {
		BookInfo info = new BookInfo();
		info.setSeq((page+1)*(i+1));
		info.setBookName(title);
		info.setScore(nums);
		info.setNums(StringUtils.substringBetween(pl, "(", "人评价"));
		System.out.println(author+"======================="+pl+"==="+title+"====="+nums+"===="+page);
		String[]items = StringUtils.split(author,"/");
		if(items.length == 5) {
			info.setAuthor(items[0]+items[1]);
			info.setPublishing(items[2]);
			info.setPublishDate(items[3]);
			info.setPrice(items[4]);
		}else if(items.length == 4) {
			info.setAuthor(items[0]);
			info.setPublishing(items[1]);
			info.setPublishDate(items[2]);
			info.setPrice(items[3]);
		}else if(items.length == 3) {
			info.setAuthor(items[0]+items[1]);
			info.setPublishDate(items[2]);
		}
		
		return info;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}