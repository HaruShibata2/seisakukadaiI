package jp.ac.teami.seisakukadaiI.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "inquiry")
public class InquiryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動インクリメント
    @Column(name = "inquiry_id")
    private Integer inquiryId;

 // UserModelとの関連
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")  // ここでuser_idを使用
    @JoinColumn(name ="username", referencedColumnName = "username")
    private UserModel user;

//    @Column(name = "name", nullable = false, length = 255)
//    private String name;

    @Column(name = "inquiry_content", nullable = false, columnDefinition = "TEXT")
    private String inquiryContent;

    @Column(name = "inquiry_description", columnDefinition = "TEXT")
    private String inquiryDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // デフォルトコンストラクタ
//    public InquiryModel() {
//        this.createdAt = LocalDateTime.now(); // 現在の日時を設定
//    }

    @Override
    public String toString() {
        return "InquiryModel{" +
                "inquiryId=" + inquiryId +
                ", user='" + user.getUserId() + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", inquiryDescription='" + inquiryDescription + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
