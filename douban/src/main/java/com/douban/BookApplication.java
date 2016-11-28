package com.douban;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BookApplication {
	
	//BookApplication.main
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		new BookApplication().start();
		long end=System.currentTimeMillis();
		System.out.println("运行时常为："+(end-start)/1000+"秒");
	}
	
	//Start
	public void start() {
		try {
			List<BookInfo> infos = new ArrayList<>();
			ExecutorService executor = Executors.newFixedThreadPool(20);
			//线程池处理5页数据
			for(int i=1; i<6; i++) {
				Document doc = Jsoup.connect(getUrl((i-1)*20)).get();
				Future<List<BookInfo>> future = executor.submit(new BookCallable(doc, i));
				infos.addAll(future.get());
			}
			
			//把5页数据采集过来后, 保存到Excel里面
			new Thread(new BookExcelRunnable(infos)).start();
			executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取每页的URL
	 * @author kangweici
	 * @date2016年11月27日下午11:02:52
	 */
	private String getUrl(int index) {
		return String.format("https://book.douban.com/tag/编程?start=%d&type=S", index);
	}
}
