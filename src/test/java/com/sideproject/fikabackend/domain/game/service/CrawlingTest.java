package com.sideproject.fikabackend.domain.game.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimeZone;

class CrawlingTest {

    public final String date = "20230515";


    @Test
    void fotmobTest() throws ParseException {

        try {

            String result = "";

            URL url = new URL("https://www.fotmob.com/api/matches?date=" + date + "&timezone=Asia%2FSeoul&ccode3=KOR");

            BufferedReader bf;


            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();


            // String 값을 JSON 형태로 추출하기 위해 사용하는 라이브러리
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray leagues = (JSONArray) jsonObject.get("leagues");
            for (Object league : leagues) {
                JSONObject obj = (JSONObject) league;
                System.out.println("나라: " + obj.get("ccode"));
                System.out.println("리그: " + obj.get("name"));
                JSONArray matches = (JSONArray) obj.get("matches");

                for (Object match : matches) {
                    JSONObject obj2 = (JSONObject) match;
                    System.out.println("시간: " + obj2.get("time"));
                    System.out.println("홈팀: " + obj2.get("home"));
                    System.out.println("어웨이팀: " + obj2.get("away"));
                }


            }


        } catch (Exception e) {
            System.out.println("잘못된 접근");
            throw new RuntimeException();
        }
        System.out.println("정상종료");
    }

}




