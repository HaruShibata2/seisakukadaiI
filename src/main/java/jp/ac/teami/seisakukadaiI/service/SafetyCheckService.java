package jp.ac.teami.seisakukadaiI.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRepository;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
public class SafetyCheckService {

    private static final Logger logger1 = LoggerFactory.getLogger(SafetyCheckService.class);

    @Autowired
    private SafetyCheckRepository safetyCheckRepository;

    @Autowired
    private UserRepository userRepository;

    // 特定ユーザーの安否確認を更新
    public void updateSafetyStatusByUsername(String username, SafetyCheck.Status status) {
        // Optional<UserModel> を使用してユーザーを取得
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("該当するユーザーが見つかりません: username=" + username));

        SafetyCheck safetyCheck = safetyCheckRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    SafetyCheck newCheck = new SafetyCheck();
                    newCheck.setUser(user);
                    return newCheck;
                });

        safetyCheck.setStatus(status);
        safetyCheck.setCheckedAt(LocalDateTime.now());
        safetyCheckRepository.save(safetyCheck);

        logger1.info("安否確認を更新しました。Username: {}, Status: {}", username, status);
    }
    


    // 手動確認フラグ
    private boolean manualCheckFlag = false;

    // 手動確認フラグを設定
    public void setManualCheckFlag(boolean flag) {
        this.manualCheckFlag = flag;
        logger1.info("Manual check flag set to: {}", flag);
    }

    // 手動確認フラグを取得
    public boolean isManualCheckFlag() {
        return manualCheckFlag;
    }

    // 全ての安否確認情報を取得するメソッド
    public List<SafetyCheck> getAllSafetyChecks() {
        logger1.info("Fetching all safety checks from the database");
        return safetyCheckRepository.findAll(); // リポジトリから全件取得
    }
}












    



