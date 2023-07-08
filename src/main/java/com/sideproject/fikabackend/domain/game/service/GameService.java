package com.sideproject.fikabackend.domain.game.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Service
public class GameService {
    public final String date = "20230701";

    @Transactional
    public void soccerGameInfo(){

        try {

            String result = "";

            URL url = new URL("https://www.fotmob.com/api/matches?date=" + date + "&timezone=Asia%2FSeoul&ccode3=KOR");

            BufferedReader bf;


            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            String gameT;
            String nation;
            String league;
            String gameDay;
            String gameTime;
            String homeTeamNm;
            String homeTeamSc;
            String awayTeamNm;
            String awayTeamSc;
            String status;

            String [] days = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

            // String 값을 JSON 형태로 추출하기 위해 사용하는 라이브러리
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray leagues = (JSONArray) jsonObject.get("leagues");
            // TODO: 2023/07/08  리그 필터링
            for (Object leagueObj : leagues) {
                JSONObject obj = (JSONObject) leagueObj;
                nation = (String) obj.get("ccode");
                league = (String) obj.get("name");

                JSONArray matches = (JSONArray) obj.get("matches");
                for (Object match : matches) {
                    JSONObject obj2 = (JSONObject) match;
                    gameT = (String) obj2.get("time");
                    JSONObject homeObj = (JSONObject) obj2.get("home");
                    JSONObject awayObj = (JSONObject) obj2.get("away");
                    JSONObject statusObj = (JSONObject) obj2.get("status");

                    homeTeamNm = (String) homeObj.get("name");
                    homeTeamSc = homeObj.get("score").toString();
                    awayTeamNm = (String) awayObj.get("name");
                    awayTeamSc = homeObj.get("score").toString();

                    String cancel = statusObj.get("cancelled").toString();
                    String start = statusObj.get("started").toString();
                    String finish = statusObj.get("finished").toString();


                    if(start.equals("true") && finish.equals("true") && cancel.equals("false")){
                        status = "finished";
                    } else if(finish.equals("true") && cancel.equals("true") && start.equals("false")){
                        status = "cancelled";
                    } else if(start.equals("true") && finish.equals("false") && cancel.equals("false")){
                        status = "started";
                    } else{
                        status = "비정상종료";
                    }

                    String gam = gameT.replaceAll("[^0-9]","");
                    String year = gam.substring(4, 8);
                    String month = gam.substring(2, 4);
                    String day = gam.substring(0, 2);
                    String hour =  gam.substring(8, 10);
                    String min = gam.substring(10, 12);

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(min));
                    cal.add(Calendar.HOUR, 7);

                    String[] s = String.format(format.format(cal.getTime())).split(" ");
                    gameDay = s[0];
                    gameTime = s[1];

                    System.out.println("나라: " + nation);
                    System.out.println("리그: " + league);
                    System.out.println("게임날짜: " + gameDay);
                    System.out.println("게임시간: " + gameTime);
                    System.out.println("홈팀: " + homeTeamNm);
                    System.out.println("홈팀스코어: " + homeTeamSc);
                    System.out.println("어웨이팀: " + awayTeamNm);
                    System.out.println("어웨이팀 스코어: " + awayTeamSc);
                    System.out.println("경기상태: " + status);
                }
            }

        } catch (Exception e) {
            System.out.println("잘못된 접근");
            e.printStackTrace();
            throw new RuntimeException();
        }
        System.out.println("정상종료");

    }



}

