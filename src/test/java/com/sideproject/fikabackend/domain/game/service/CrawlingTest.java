package com.sideproject.fikabackend.domain.game.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimeZone;

class CrawlingTest {

    public final String date = "20230515";


    @Test
    void fotmobTest() throws Exception {

        try {

            String result = "";

            URL url = new URL("https://www.fotmob.com/api/matches?date="+date);
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
            BufferedReader bf;


            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();


            // String 값을 JSON 형태로 추출하기 위해 사용하는 라이브러리
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray addResult = (JSONArray) jsonObject.get("leagues");

            JSONObject england = (JSONObject) addResult.get(0);

            String country = england.get("ccode").toString();
            String league = england.get("name").toString();
            String match = england.get("matches").toString();

            System.out.println("***************************");
            System.out.println("***************************");
            System.out.println(country);
            System.out.println(league);
            System.out.println(match);



        } catch (Exception e) {
            System.out.println("잘못된 접근");
            throw new RuntimeException();
        }
        System.out.println("정상종료");
    }

}




