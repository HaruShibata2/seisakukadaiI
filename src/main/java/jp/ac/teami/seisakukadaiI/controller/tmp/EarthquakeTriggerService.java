package jp.ac.teami.seisakukadaiI.controller.tmp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EarthquakeTriggerService {

    // 30秒後に地震を発生させるメソッド
    @Scheduled(initialDelay = 20000, fixedRate = Long.MAX_VALUE) // 初期遅延を30秒、固定間隔を長期間に設定
    public void triggerEarthquake() {
        // 地震のシミュレーションまたは地震情報を取得する処理をここに追加
        System.out.println("地震が発生しました: " + System.currentTimeMillis());
    }
}
