package jp.ac.teami.seisakukadaiI.dto;

import java.io.Serializable;

public class SafetyResponseDto implements Serializable {
    
    private Long userId;
    private Long safetyCheckRequestId;
    private boolean isSafe;
    private String injurySeverity;

    // デフォルトコンストラクタ
    public SafetyResponseDto() {
    }

    // 全フィールドを含むコンストラクタ
    public SafetyResponseDto(Long userId, Long safetyCheckRequestId, boolean isSafe, String injurySeverity) {
        this.userId = userId;
        this.safetyCheckRequestId = safetyCheckRequestId;
        this.isSafe = isSafe;
        this.injurySeverity = injurySeverity;
    }

    // Getter and Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSafetyCheckRequestId() {
        return safetyCheckRequestId;
    }

    public void setSafetyCheckRequestId(Long safetyCheckRequestId) {
        this.safetyCheckRequestId = safetyCheckRequestId;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean isSafe) {
        this.isSafe = isSafe;
    }

    public String getInjurySeverity() {
        return injurySeverity;
    }

    public void setInjurySeverity(String injurySeverity) {
        this.injurySeverity = injurySeverity;
    }

    @Override
    public String toString() {
        return "SafetyResponseDto{" +
                "userId=" + userId +
                ", safetyCheckRequestId=" + safetyCheckRequestId +
                ", isSafe=" + isSafe +
                ", injurySeverity='" + injurySeverity + '\'' +
                '}';
    }
}
