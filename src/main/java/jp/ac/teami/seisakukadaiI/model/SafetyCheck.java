package jp.ac.teami.seisakukadaiI.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "safetychecks")
public class SafetyCheck {


    // 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_id")
    private Integer safetyId;
    

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserModel user;

    @Enumerated(EnumType.STRING)
    private Status status; // 安否状態

    @Column(name = "checked_at", updatable = false)
    private LocalDateTime checkedAt; // 安否確認日時


    public enum Status {
    	SAFE,   // 状態: 安全
    	INJURED // 状態: 怪我
    }
}