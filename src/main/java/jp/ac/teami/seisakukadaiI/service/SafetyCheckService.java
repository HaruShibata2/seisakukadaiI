package jp.ac.teami.seisakukadaiI.service;

import jp.ac.teami.seisakukadaiI.dto.SafetyResponseDto;
import jp.ac.teami.seisakukadaiI.model.SafetyCheckRequest;
import jp.ac.teami.seisakukadaiI.model.SafetyResponse;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRequestRepository;
import jp.ac.teami.seisakukadaiI.repository.SafetyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SafetyCheckService {

    @Autowired
    private SafetyCheckRequestRepository safetyCheckRequestRepository;

    @Autowired
    private SafetyResponseRepository safetyResponseRepository;

    // 安全確認のリクエストをデータベースに作成
    public void createSafetyCheckRequest() {
        SafetyCheckRequest request = new SafetyCheckRequest();
        request.setCreatedAt(LocalDateTime.now());
        request.setStatus("進行中");
        safetyCheckRequestRepository.save(request);
    }

    // ユーザーの安否応答を保存
    public void saveUserResponse(SafetyResponseDto responseDto) {
        SafetyResponse response = new SafetyResponse();
        response.setUserId(responseDto.getUserId());
        response.setSafetyCheckRequestId(responseDto.getSafetyCheckRequestId());
        response.setSafe(responseDto.isSafe());
        response.setInjurySeverity(responseDto.getInjurySeverity());
        safetyResponseRepository.save(response);
    }
}
