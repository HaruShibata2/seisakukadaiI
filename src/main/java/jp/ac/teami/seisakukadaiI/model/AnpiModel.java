package jp.ac.teami.seisakukadaiI.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anpi_confirmation")
public class AnpiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動インクリメント
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "earthquake_id", nullable = false)
    private Integer earthquakeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "confirmation_time", nullable = false)
    private LocalDateTime confirmationTime;

    // Status enum の定義
    public enum Status {
        SAFE,
        IN_DANGER,
        UNKNOWN
    }

    // コンストラクタ
    public AnpiModel() {
    }

    // ゲッターとセッター
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(Integer earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    @Override
    public String toString() {
        return "AnpiModel{" +
                "userId=" + userId +
                ", earthquakeId=" + earthquakeId +
                ", status=" + status +
                ", confirmationTime=" + confirmationTime +
                '}';
    }
}
