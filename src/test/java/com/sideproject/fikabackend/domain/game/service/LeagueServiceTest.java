package com.sideproject.fikabackend.domain.game.service;

import net.minidev.json.JSONUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

class LeagueServiceTest {

    @Test
    @DisplayName("fotmob API test")
    void getData() throws IOException {

        int leagueId = 47;

        URL url = new URL("https://www.fotmob.com/api/leagues?id=" + leagueId + "&tab=matches&type=league&timeZone=Asia%2FSeoul");

        BufferedReader bf;

        bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        String result = bf.readLine();
        System.out.println("result = " + result);
        bf.close();
    }

    @Test
    @DisplayName("restTemplate test")
    void restTemplate() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        final int leagueId = 42;
        final String tab = "matches";
        final String type = "league";
        final String timeZone = "Asia%2FSeoul";

        String url = "https://www.fotmob.com/api/leagues?id={id}&tab={tab}&type={type}&timeZone={timeZone}";

        String object = restTemplate.getForObject(url, String.class, leagueId, tab, type, timeZone);

        JSONParser jsonParser = new JSONParser();
        JSONObject parse = (JSONObject) jsonParser.parse(object);
        JSONObject matches = (JSONObject) parse.get("matches");
        JSONArray allMatches = (JSONArray) matches.get("allMatches");
        JSONObject game = (JSONObject) allMatches.get(0);
        long round = (game.get("round") != null)? (long) game.get("round") : 0L;
        String matchId = (String) game.get("id");
        JSONObject home = (JSONObject) game.get("home");
        String homeTeamId = (String) home.get("id");
        String homeTeamNm = (String) home.get("name");
        JSONObject away = (JSONObject) game.get("away");
        String awayTeamId = (String) away.get("id");
        String awayTeamNm = (String) away.get("name");
        JSONObject status = (JSONObject) game.get("status");
        String utcTime = (String) status.get("utcTime");

        String[] utcTimes = utcTime.split("T");
        String[] dates = utcTimes[0].split("-");
        String[] times = utcTimes[1].split(":");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        cal.set(Integer.parseInt(dates[0]),
                Integer.parseInt(dates[1]),
                Integer.parseInt(dates[2]),
                Integer.parseInt(times[0]),
                Integer.parseInt(times[1]));
        cal.add(Calendar.HOUR, 7);
        String[] convertDt = String.format(format.format(cal.getTime())).split(" ");

        String date = convertDt[0];
        String time = convertDt[1];
        boolean finished = (boolean) status.get("finished");
        boolean started = (boolean) status.get("started");
        boolean cancelled = (boolean) status.get("cancelled");
        String scoreStr = (String) status.get("scoreStr");
        String[] scores = scoreStr.split("-");
        System.out.println("scores = " + scores[0].replaceAll(" ", ""));
        System.out.println("round = " + round);
        System.out.println("matchId = " + matchId);
        System.out.println("home = " + home);
        System.out.println("homeTeamId = " + homeTeamId);
        System.out.println("homeTeamNm = " + homeTeamNm);
        System.out.println("away = " + away);
        System.out.println("awayTeamId = " + awayTeamId);
        System.out.println("awayTeamNm = " + awayTeamNm);
        System.out.println("status = " + status);
        System.out.println("utcTime = " + utcTime);
        System.out.println("date = " + date);
        System.out.println("time = " + time);
        System.out.println("finished = " + finished);
        System.out.println("started = " + started);
        System.out.println("cancelled = " + cancelled);
        System.out.println("scoreStr = " + scoreStr);

    }

}