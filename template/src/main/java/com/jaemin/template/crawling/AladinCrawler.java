package com.jaemin.template.crawling;

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

		Elements tlist = doc.select("div.tlist");
		Elements box = doc.select("div.Ere_prod_mconts_box");
		Elements body = doc.select("div.Ere_prod_middlewrap");
		Elements testbody = doc.select("div");
		Iterator<Element> titleAllItr = tlist.select("li.Ere_sub2_title").iterator();
		Iterator<Element> titleItr = tlist.select("a.Ere_sub2_title").iterator();		
		Iterator<Element> infoItr = box.select("div.conts_info_list1 li").iterator();	
		Iterator<Element> contentItr = body.select(".Ere_prod_mconts_box").iterator();
		Iterator<Element> testItr = body.select(".blog_list2").iterator();
		
		Element bookNameEle = doc.select("a.Ere_bo_title").iterator().next();
		
		System.out.println("================================\n\n\n\n");
		
		
		String bName = bookNameEle.text();
		System.out.println("bName : " + bName);
		String titleAll = titleAllItr.next().text();
		
//		System.out.println("titleAll : " + titleAll);
		if (titleAll.contains("원제")) {
			titleAll = titleAll.substring(0, titleAll.indexOf("원제"));
		}
		String bPubdate = titleAll.substring(titleAll.length() - 10, titleAll.length());
		System.out.println("bPubdate : " + bPubdate);
		
		int commaCnt = 0;
		char[] titleAllChar = titleAll.toCharArray();
		
		for (int i = 0; i < titleAllChar.length; i++) {
			if (titleAllChar[i] == ',') {
				commaCnt++;
			}
		}
		
		if (titleItr.hasNext()) {
			String bAuthor = titleItr.next().text();
			System.out.println("bAuthor : " + bAuthor);
		}
		
		while (titleItr.hasNext()) {
			if (commaCnt > 0) {
				titleItr.next();
				commaCnt--;
			} else {
				String bPub = titleItr.next().text();
				System.out.println("bPub : " + bPub);
				break;
			}
		}
		
		String bCode = null;
		String bPage = null;
		while (infoItr.hasNext()) {
			String dat = infoItr.next().text();
//			System.out.println("dat : " + dat);
			
			if (dat.contains("쪽")) {
				bPage = dat.substring(0, dat.length() - 1);
			} else if(dat.contains("ISBN")) {
				bCode = dat.substring(7);
			}
		}
		
		System.out.println("bCode : " + bCode);
		System.out.println("bPage : " + bPage);
		
		System.out.println();
		System.out.println();
		while (contentItr.hasNext()) {
			String cont = contentItr.next().text();
			System.out.println(cont);
		}
		System.out.println("\n\n\n\n");
		while (testItr.hasNext()) {
			String test = testItr.next().text();
			System.out.println(test);
		}

		System.out.println("\n\n\n\n=======================================");
	}
}
