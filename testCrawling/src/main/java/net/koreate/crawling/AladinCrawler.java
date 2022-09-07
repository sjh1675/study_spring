package net.koreate.crawling;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AladinCrawler implements Crawler {
	
	@Override
	public void crawling(String url) {
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements e = doc.select("div.tlist");
		
		System.out.println("================================");
		
		Iterator<Element> ie1 = e.select("a.Ere_bo_title").iterator();
		
		while (ie1.hasNext()) {
			System.out.println(ie1.next().text());
		}
		
		System.out.println("===============================");		
	}
}
