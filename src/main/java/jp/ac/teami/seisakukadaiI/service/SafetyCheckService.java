package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.EarthquakeResponse;
import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRepository;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
public class SafetyCheckService {

    @Autowired
    private SafetyCheckRepository safetyCheckRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendAutomaticSafetyCheck(EarthquakeResponse earthquakeResponse) {
        // 地震の情報を基に安否確認を送信
        for (EarthquakeResponse.EarthquakeFeature feature : earthquakeResponse.getFeatures()) {
            double magnitude = feature.getProperties().getMag();
            if (magnitude >= 4.0) {  // 震度が4.0以上であれば
                // ユーザー全員に安否確認を送る処理
                userRepository.findAll().forEach(user -> {
                    SafetyCheck safetyCheck = new SafetyCheck();
                    safetyCheck.setUser(user);
                    safetyCheck.setStatus(SafetyCheck.Status.SAFE);  // 安全をデフォルト設定
                    safetyCheckRepository.save(safetyCheck);
                });
                //System.out.println("安否確認を全ユーザーに自動送信しました！");
            }
        }
    }
}

    



