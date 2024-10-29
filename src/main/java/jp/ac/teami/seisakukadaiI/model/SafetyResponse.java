package jp.ac.teami.seisakukadaiI.model;



import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "safety_responses")
public class SafetyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long safetyCheckRequestId;
    private boolean isSafe;
    private String injurySeverity;

    // コンストラクタ、ゲッター、セッター

    public SafetyResponse() {
        //
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
