package com.sideproject.fikabackend.address;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DistanceCalculateTest {

    private final int RADIUS_EARTH = 6371;
    private final double RADIAN = Math.PI / 180;

    @Test
    @DisplayName("도 단위로 위도, 경도 값 구하기")
    void getLatAndLon() {
        // 도 단위
        int degree = 1;
        // cos(위도, 35.8448도로 가정)
        double cos = Math.cos(Math.toRadians(35.8448));
        // 위도
        double latitude = RADIUS_EARTH * degree * RADIAN;
        // 경도
        double longitude = RADIUS_EARTH * degree * RADIAN * cos;

        assertThat(latitude).isEqualTo(111.19492664455873);
        assertThat(longitude).isEqualTo(90.13529564631034);
    }

    @Test
    @DisplayName("위도, 경도로 두 지점 간의 거리 구하기")
    void getDistance() {
        // 역삼역 위도, 경도
        double firstLat = 37.5005969905977;
        double firstLon = 127.03627943874547;
        // 삼성역 위도, 경도
        double lastLat = 37.50886598360025;
        double lastLon = 127.06303229607052;
        // 위도, 경도의 차이를 라디안 값으로 변환
        double dLat = Math.toRadians(lastLat - firstLat);
        double dLon = Math.toRadians(lastLon - firstLon);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(firstLat)) * Math.cos(Math.toRadians(lastLat))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = RADIUS_EARTH * c * 1000; // m 단위
        // 98% 의 근사치, 역삼역과 삼성역의 거리는 약 2.5km
        assertThat(d).isCloseTo(2500, Percentage.withPercentage(2));
    }

    @Test
    @DisplayName("기준 좌표에서 특정 거리 이내의 좌표 구하기")
    void getAround() {
        // 현재 위치 : 금결원
        // 현재 위도 좌표 (y 좌표)
        double nowLat = 37.50015499725032;
        // 현재 경도 좌표 (x 좌표)
        double nowLon = 127.03787379177015;
        // 특정 거리
        int distance = 500;

        // m 당 y 좌표 이동 값
        double mForLat = (1 / (RADIUS_EARTH * 1 * RADIAN)) / 1000;
        // m 당 x 좌표 이동 값
        double mForLon = (1 / (RADIUS_EARTH * 1 * RADIAN) * Math.cos(Math.toRadians(nowLat))) / 1000;

        // 현재 위치 기준 검색 거리 좌표
        double maxY = nowLat + (distance * mForLat);
        double minY = nowLat - (distance * mForLat);
        double maxX = nowLon + (distance * mForLon);
        double minX = nowLon - (distance * mForLon);

        // 역삼역의 좌표는 현재 위치인 금결원에서 500m 내에 있어야 한다.
        // 위도 값 확인
        double latYeokSam = 37.5005969905977;
        assertThat(latYeokSam).isBetween(minY, maxY);
        // 경도 값 확인
        double lonYeokSam = 127.03627943874547;
        assertThat(lonYeokSam).isBetween(minX, maxX);

        // 삼성역의 좌표는 현재 위치인 금결원에서 500m 내에 없어야 한다.
        // 위도 값 확인
        double latSamSung = 37.50886598360025;
        assertFalse(minY < latSamSung && latSamSung < maxY);
        // 경도 값 확인
        double lonSamSung = 127.06303229607052;
        assertFalse(minX < lonSamSung && lonSamSung < maxX);
    }
}
