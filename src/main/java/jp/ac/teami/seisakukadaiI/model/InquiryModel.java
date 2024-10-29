package jp.ac.teami.seisakukadaiI.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inquiries")
public class InquiryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動インクリメント
    @Column(name = "inquiry_id")
    private Integer inquiryId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "inquiry_content", nullable = false, columnDefinition = "TEXT")
    private String inquiryContent;

    @Column(name = "inquiry_description", columnDefinition = "TEXT")
    private String inquiryDescription;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // コンストラクタ
    public InquiryModel() {
    }

    // ゲッターとセッター
    public Integer getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public String getInquiryDescription() {
        return inquiryDescription;
    }

    public void setInquiryDescription(String inquiryDescription) {
        this.inquiryDescription = inquiryDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "InquiryModel{" +
                "inquiryId=" + inquiryId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", inquiryDescription='" + inquiryDescription + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
