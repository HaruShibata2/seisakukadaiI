package jp.ac.teami.seisakukadaiI.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "safety_responses")
@IdClass(AnpikaitouModel.class)
public class AnpikaitouModel {
 
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
 
    @Id
    @Column(name = "earthquake_id", nullable = false)
    private Integer earthquakeId;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "answer", length = 50, nullable = false)
    private SafetyAnswer answer;
 
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
 
    public SafetyAnswer getAnswer() {
        return answer;
    }
 
    public void setAnswer(SafetyAnswer answer) {
        this.answer = answer;
    }
 
    // Enum for Safety Answer
    public enum SafetyAnswer {
        SAFE,        // 無事
        INJURED,     // けがあり
        UNKNOWN      // 安否不明
    }
}
 
 