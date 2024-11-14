package jp.ac.teami.seisakukadaiI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRepository;

@Service
public class SafetyCheckService {

    private final SafetyCheckRepository safetyCheckRepository;

    // コンストラクタインジェクションに変更（推奨される方法）
    public SafetyCheckService(SafetyCheckRepository safetyCheckRepository) {
        this.safetyCheckRepository = safetyCheckRepository;
    }

    /**
     * 指定された状態の安全確認を取得するメソッド
     * 
     * @param status 取得する安全確認の状態
     * @return 指定された状態に一致する SafetyCheck のリスト
     */
    public List<SafetyCheck> findSafetyChecksByStatus(String status) {
        return safetyCheckRepository.findByStatus	(status);
    }

    /**
     * すべての安全確認を取得するメソッド
     * 
     * @return 全 SafetyCheck のリスト
     */
    public List<SafetyCheck> findAllSafetyChecks() {
        return safetyCheckRepository.findAll();
    }

    /**
     * 新しい安全確認を保存するメソッド
     * 
     * @param user   対応するユーザー
     * @param status 安全確認の状態
     */
    public void saveSafetyCheck(UserModel user, String status) {
        SafetyCheck safetyCheck = new SafetyCheck();
        safetyCheck.setUser(user);
        safetyCheck.setStatus(SafetyCheck.Status.valueOf(status.toUpperCase()));
        safetyCheckRepository.save(safetyCheck);
    }

    }
    



