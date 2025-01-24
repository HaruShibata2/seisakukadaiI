	package jp.ac.teami.seisakukadaiI.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jp.ac.teami.seisakukadaiI.model.EarthquakeResponse;

@Service
public class EarthquakeService {

    private final RestTemplate restTemplate;
    private final SafetyCheckService safetyCheckService;

    private static final String BASE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    private static final double LATITUDE = 33.5902; // 福岡市の緯度
    private static final double LONGITUDE = 130.4017; // 福岡市の経度
    private static final double MAX_RADIUS = 5.0; // 半径（単位：度）
    private static final double MIN_MAGNITUDE = 4.0; // 最小震度

    public EarthquakeService(RestTemplate restTemplate, SafetyCheckService safetyCheckService) {
        this.restTemplate = restTemplate;
        this.safetyCheckService = safetyCheckService;
    }

    @Scheduled(fixedDelay = 10000) // 10秒ごとに地震データをチェック
    public void triggerEarthquakeDataFetch() {
        try {
            EarthquakeResponse response = getEarthquakeData();
            response.getFeatures().forEach(feature -> {
                double magnitude = feature.getProperties().getMag();
                if (magnitude >= 5.0) {
                    // 震度5以上の場合、自動で「安否確認を送信しますか？」画面を表示
                    safetyCheckService.setManualCheckFlag(true); // 手動確認フラグを設定
                } else if (magnitude >= 3.0) {
                    // 震度3以上4未満の場合、別の確認処理
                    safetyCheckService.setManualCheckFlag(true);
                } else {
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("地震データの取得に失敗しました", e);
        }
    }


    public EarthquakeResponse getEarthquakeData() {
        String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("format", "geojson")
                .queryParam("latitude", LATITUDE)
                .queryParam("longitude", LONGITUDE)
                .queryParam("maxradius", MAX_RADIUS)
                .queryParam("minmagnitude", MIN_MAGNITUDE)
                .toUriString();

        try {
            // 地震情報を取得
            EarthquakeResponse response = restTemplate.getForObject(uri, EarthquakeResponse.class);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch earthquake data", e);
        }
    }
}
