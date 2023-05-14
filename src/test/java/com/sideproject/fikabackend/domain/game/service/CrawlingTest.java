package com.sideproject.fikabackend.domain.game.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.Doc;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


class CrawlingTest {
    public  WebDriver driver;

    public final String url = "https://sports.news.naver.com/wfootball/schedule/index";


    @Test
    void crawling() throws Exception {

        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "/Users/taehyeonkim/Downloads/chromedriver_mac64/chromedriver"); // 리눅스, 맥

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");
        options.addArguments("--start-maximized"); // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        options.addArguments("--disable-default-apps"); // 기본앱 사용안함

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        try {
            List<WebElement> listAll = driver.findElements(By.cssSelector("tbody#_monthlyScheduleList > tr"));

            String date = null;
            for(WebElement li :listAll) {
               List<WebElement> test = li.findElements(By.cssSelector("th"));

                if(test.size() > 0) {
                    WebElement date1 = test.get(0);
                    date = date1.getText();
                }

                String time = li.findElement(By.cssSelector("td.time_place > div.inner > .time")).getText();
                String place = li.findElement(By.cssSelector("td.time_place > div.inner > .place")).getText();
                String team1 = li.findElement(By.cssSelector("span.team_left")).getText();
                String team2 = li.findElement(By.cssSelector("span.team_right")).getText();

                System.out.println("date: " + date);
                System.out.println("gametime: " + time);
                System.out.println("gameplace: " + place);
                System.out.println("leftteam: " + team1);
                System.out.println("rightteam: " + team2);

            }





//            List<WebElement> listAll = driver.findElements(By.cssSelector("tbody#_monthlyScheduleList"));
//            List<WebElement> list =driver.findElements(By.cssSelector("tbody#_monthlyScheduleList > tr.division"));
//            System.out.println("LLIISSTT: " + list.size());
//            System.out.println("*************************");
//            System.out.println("*************************");
//            for (WebElement l : list) {
//                System.out.println("LLIISSTT: " + list.size());
//                System.out.println("*************************");
//                System.out.println("*************************");
//                System.out.println("경기일자" + l.findElement(By.cssSelector("#_monthlyScheduleList > tr:nth-child(" + i + ") > th > div")).getText());
//                for(int i = 0; i < list.size(); i++){
//
//
//                }
//
//            }
        } finally {
            driver.quit();
        }
    }
}




