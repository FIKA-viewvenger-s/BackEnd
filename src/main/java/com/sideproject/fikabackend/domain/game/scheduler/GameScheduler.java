package com.sideproject.fikabackend.domain.game.scheduler;

import com.sideproject.fikabackend.domain.game.dto.GameDto;
import com.sideproject.fikabackend.domain.game.entity.Game;
import com.sideproject.fikabackend.domain.game.entity.LeagueType;
import com.sideproject.fikabackend.domain.game.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 각 리그의 경기 일정 저장
 */
@Slf4j
public class GameScheduler {

    private final GameRepository gameRepository;

    private final List<Game> games = new ArrayList<>();

    public GameScheduler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * 매년 7월 1일 새벽 3시에 실행
     */
//    @Scheduled(cron = "0 0 3 1 7 ? *")
//    @EventListener(ApplicationReadyEvent.class)
    public void save() {

        for (LeagueType leagueType : LeagueType.values()) {
            getGames(leagueType);
        }
        gameRepository.saveAll(games);
    }

    private void getGames(LeagueType leagueType) {

        final String logoUrl = "https://images.fotmob.com/image_resources/logo/teamlogo/";
        final String logoExtension = ".png";
        final String tab = "matches";
        final String type = "league";
        final String timeZone = "Asia%2FSeoul";
        final String url = "https://www.fotmob.com/api/leagues?id={id}&tab={tab}&type={type}&timeZone={timeZone}";

        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject(url, String.class, leagueType.getLeagueId(), tab, type, timeZone);
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject parsingData = (JSONObject) jsonParser.parse(object);
            JSONObject matches = (JSONObject) parsingData.get("matches");
            JSONArray allMatches = (JSONArray) matches.get("allMatches");
            for (Object allMatch : allMatches) {
                JSONObject match = (JSONObject) allMatch;
                long round = (long) match.get("round");
                String matchId = (String) match.get("id");
                JSONObject home = (JSONObject) match.get("home");
                String homeId = (String) home.get("id");
                String homeNm = (String) home.get("name");
                String homeImg = logoUrl + homeId + logoExtension;
                JSONObject away = (JSONObject) match.get("away");
                String awayId = (String) away.get("id");
                String awayNm = (String) away.get("name");
                String awayImg = logoUrl + awayId + logoExtension;
                JSONObject status = (JSONObject) match.get("status");
                String utcTime = (String) status.get("utcTime");
                String[] convertDt = getConvertDt(utcTime);

                GameDto gameDto = GameDto.builder()
                        .gmRound(round)
                        .gmId(Long.valueOf(matchId))
                        .gmTp(leagueType.getLeagueTp())
                        .gmLeague(leagueType.getLeagueNm())
                        .gmHomeId(homeId)
                        .gmHome(homeNm)
                        .gmHomeImg(homeImg)
                        .gmAwayId(awayId)
                        .gmAway(awayNm)
                        .gmAwayImg(awayImg)
                        .gmDate(convertDt[0])
                        .gmTime(convertDt[1])
                        .build();

                Game game = new Game(gameDto);
                games.add(game);
            }
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
    }

    private String[] getConvertDt(String utcTime) {
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
        return String.format(format.format(cal.getTime())).split(" ");
    }
}
