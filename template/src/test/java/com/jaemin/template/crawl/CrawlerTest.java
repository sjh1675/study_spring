package com.jaemin.template.crawl;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jaemin.template.config.AppConfig;
import com.jaemin.template.config.RootConfig;
import com.jaemin.template.config.WebConfig;
import com.jaemin.template.crawling.Celenium;
import com.jaemin.template.dao.TempDAO;
import com.jaemin.template.vo.TempVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class, RootConfig.class, AppConfig.class })
@WebAppConfiguration
public class CrawlerTest {

	@Autowired
	TempDAO tpdao;
	@Autowired
	ServletContext context;
	
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe"; // 드라이버 경로

	@Test
	public void 크롤러_테스트() throws Exception {
		// 드라이버 설정
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			System.out.println("드라이버 등록");
		} catch (Exception e) {
			System.out.println("오류 발생");
		}
		WebDriver driver = null;
		Celenium c = new Celenium();
		try {

			for (int i = 1; i <= 6; i++) { // 25 X 40 = 1000개 게시물 검색 (ViewRowsCount X 포문 반복횟수)

				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);

				int cid = 170;
				// 2105 : 고전
				// 170 : 경제
				// 517 : 예술/대중문화
				// 74 : 역사
				// 987 : 과학 
				// 656 : 인문학
				
				String imgFolderPath = context.getRealPath(File.separator + "crawlingimg");
				System.out.println("imgPath : " + imgFolderPath);
				List<TempVO> voList = c.crawling(driver,
						"https://www.aladin.co.kr/shop/wbrowse.aspx?BrowseTarget=List&ViewRowsCount=25&ViewType=Detail&PublishMonth=0&SortOrder=2&page="
								+ i + "&Stockstatus=1&PublishDay=84&CID=" + cid, imgFolderPath);

				for (TempVO vo : voList) {
					tpdao.insert(vo);
				}
				if (driver != null) {
					driver.close();
					driver.quit();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
		}
	}

}
