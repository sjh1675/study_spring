package com.koreate.betty.domain.book.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.koreate.betty.domain.book.dto.form.BookForm;
import com.koreate.betty.domain.book.vo.Book;

public class Selenium {

	private static final List<List<String>> map;

	static {

		map = new ArrayList<List<String>>();
		map.add(Arrays.asList("인문학", "철학"));
		map.add(Arrays.asList("종교", "역학", "기독교", "개신교", "신앙", "가톨릭", "불교", "천주교"));
		map.add(Arrays.asList("사회", "경제", "정치", "외교학", "행정", "법", "생활", "국방", "군사"));
		map.add(Arrays.asList("과학", "천문학", "수학", "물리학", "화학", "동물", "식물", "곤충", "생물"));
		map.add(Arrays.asList("공학", "기계", "전기", "전자", "전자", "토목", "건축", "화학", "환경", "농업", "의학"));
		map.add(Arrays.asList("예술", "대중문화", "사진", "컬러링", "미학", "미술", "화집", "음악", "악보", "작곡", "영화", "드라마", "시나리오", "디자인",
				"공예", "뮤지컬", "오페라", "공연기획", "무용", "연극", "TV", "라디오"));
		map.add(Arrays.asList("외국어", "어학사전", "토익", "토플", "텝스", "영어", "영문법", "일본어", "중국어", "한자", "독일어", "러시아어", "스페인어",
				"이탈리아어", "프랑스어", "영작문", "서양어", "동양어", "통역", "번역"));
		map.add(Arrays.asList("소설", "시", "희곡", "에세이", "판타지", "문학", "고전", "산문"));
		map.add(Arrays.asList("역사", "세계사", "동양사", "서양사"));
	}

	public static String genreFind(String genre) {

		boolean find = false;
		int init = 0;

		allList: for (List<String> strList : map) { // 도는 순서가 보장이 되지않음
			init += 100;
			for (String str : strList) {
				if (genre.contains(str)) {
					find = true;
					break allList;
				}
			}
		}
		if (find == false) {
			return "000";
		}
		return String.valueOf(init);
	}

	private static String downImages(String imgFolderPath, String imgURL, String isbn) {

		System.out.println("imgFolderPath : " + imgFolderPath);		
		
		String[] fileName = imgURL.substring(imgURL.lastIndexOf("/")).split("/");

		File files = new File(imgFolderPath);

		if (!files.exists()) {
			System.out.println("폴더 생성");
			files.mkdirs();
		}

		try {
			URL url = new URL(imgURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
			isbn += ".jpg";
			File file = new File(imgFolderPath, isbn);
//			System.out.println("파일 생성 여부(데이터 존재여부 모름) : " + file.createNewFile());
//			System.out.println("파일 생성 경로 : " + file.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;

			while ((i = is.read()) != -1) {
				out.write(i);
			}

			is.close();
			out.flush();
			out.close();
			
			return isbn.toString(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Book> crawling(WebDriver driver, String url, String imgFolderPath) throws Exception {

		WebElement titleEle;
		List<WebElement> titles;
		List<WebElement> infoList;
		List<WebElement> contList;
		List<WebElement> boardList;

		List<String> urlList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();

		driver.get(url);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		boardList = driver.findElements(By.className("bo3"));

		for (WebElement board : boardList) {
			urlList.add(board.getAttribute("href"));
		}

		for (String newUrl : urlList) {
			System.out.println(newUrl);
			driver.get(newUrl);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			if (ExpectedConditions.alertIsPresent().apply(driver) != null) {
				driver.switchTo().alert().accept();
				continue;
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			int t = 50;
			int dif = 0;
			while (t-- > 0) {
				dif += 200;
				js.executeScript("window.scrollTo(0, " + dif + ")");
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
				}
			}

			titleEle = driver.findElement(By.className("Ere_bo_title"));
			WebElement tlist = driver.findElement(By.className("tlist"));
			titles = tlist.findElements(By.className("Ere_sub2_title"));

			List<WebElement> infoAlls = driver.findElements(By.className("conts_info_list1"));

			String code = null;
			String page = null;
			for (WebElement infoAll : infoAlls) {
				infoList = infoAll.findElements(By.tagName("li"));

				for (WebElement info : infoList) {
					String dat = info.getText();
					if (dat.contains("쪽")) {
						page = dat.substring(0, dat.length() - 1);
					} else if (dat.contains("ISBN")) {
						code = dat.substring(7);
					}
				}
			}
			if (code == null)
				continue;

			contList = driver.findElements(By.className("Ere_prod_mconts_box"));

			String title = titleEle.getText();
			if (title.contains("중고"))
				continue;

			String titleAll = titles.get(0).getText();
			
			if (titleAll.contains("원제")) {
				titleAll = titleAll.substring(0, titleAll.indexOf("원제"));
			}
			
			if (titleAll.contains("초판출간")) {
				titleAll = titleAll.substring(0, titleAll.indexOf("초판출간"));
			}
			
			String pubdateStr = titleAll.substring(titleAll.length() - 10, titleAll.length());

			int commaCnt = 0;
			char[] titleAllChar = titleAll.toCharArray();

			for (int i = 0; i < titleAllChar.length; i++) {
				if (titleAllChar[i] == ',') {
					commaCnt++;
				}
			}
			String auth = titles.get(1).getText();
			String pub = null;
			for (int i = 2; i < titles.size(); i++) {
				if (commaCnt > 0) {
					commaCnt--;
					continue;
				} else {
					pub = titles.get(i).getText();
					break;
				}
			}

			String intro = null;
			for (WebElement cont : contList) {
				String text = cont.getText();
				if (text.contains("책소개")) {
					intro = text;
					break;
				}
			}
			
			if (intro == null) {
				System.out.println("책 소개가 없습니다.");
				continue;
			}

			String data = driver.findElement(By.id("ulCategory")).getText();
			System.out.println(data);
			String genreStr = genreFind(data);

			String imgURL = driver.findElement(By.id("CoverMainImage")).getAttribute("src");

			String img = downImages(imgFolderPath, imgURL, code);
			if (img == null) continue;			
			
			System.out.println(code);
			System.out.println(title);
			System.out.println(auth);
			System.out.println(pub);
			System.out.println(pubdateStr);
			System.out.println(page);
			System.out.println(genreStr);
			System.out.println(img);
			System.out.println(intro);

			int pageCnt = Integer.parseInt(page);
			
//			Timestamp pubDate = Timestamp.valueOf(pubdateStr + " 00:00:00");
			int genre = Integer.parseInt(genreStr);
			
			
			BookForm form = new BookForm(code, title, auth, pub, pubdateStr, pageCnt, genre, intro);
			Book book = form.createBook();

			bookList.add(book);
		}
		return bookList;
	}
}