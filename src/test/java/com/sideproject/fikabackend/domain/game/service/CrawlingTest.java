package com.sideproject.fikabackend.domain.game.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.print.Doc;
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
            List<WebElement> list = driver.findElements(By.id("_monthlyScheduleList"));
            System.out.println("LLIISSTT: " + list.size());
            for (WebElement l : list) {
                System.out.println("*************************");
                System.out.println("*************************");
                System.out.println("*************************");
                System.out.println(l.getText());
//                for(int i = 0; i < list.size(); i++){
//                    System.out.println(l.findElement(By.className("division")).getText());
//                }
            }
        } finally {
            driver.quit();
        }
    }
}




