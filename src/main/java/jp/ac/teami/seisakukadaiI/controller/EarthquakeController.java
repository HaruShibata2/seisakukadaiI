package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.model.EarthquakeResponse;
import jp.ac.teami.seisakukadaiI.service.EarthquakeService;

@Controller
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    public EarthquakeController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @GetMapping("/fetch")
    public String fetchEarthquakeData(Model model) {
        // 地震データを取得
        EarthquakeResponse response = earthquakeService.getEarthquakeData();

        // モデルに地震データを追加
        model.addAttribute("earthquakeData", response.getFeatures());

        // Thymeleafテンプレートにデータを渡す
        return "safety/earthquake";  // earthqueake.htmlテンプレートを表示
    }
}

