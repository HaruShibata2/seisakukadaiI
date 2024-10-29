package jp.ac.teami.seisakukadaiI.controller;




import jp.ac.teami.seisakukadaiI.dto.SafetyResponseDto;
import jp.ac.teami.seisakukadaiI.service.SafetyCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/safety-check")
public class SafetyCheckController {

    @Autowired
    private SafetyCheckService safetyCheckService;

    // 安全確認を開始するリクエスト（権限者のみが実行可能）
    @PostMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> requestSafetyCheck() {
        safetyCheckService.createSafetyCheckRequest();
        return ResponseEntity.ok("Safety check started.");
    }

    // ユーザーが安全確認に応答するリクエスト
    @PostMapping("/response")
    public ResponseEntity<String> respondSafetyCheck(@RequestBody SafetyResponseDto responseDto) {
        safetyCheckService.saveUserResponse(responseDto);
        return ResponseEntity.ok("Response recorded.");
    }
}

