package jp.ac.teami.seisakukadaiI.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
a
@Entity
@Table(name = "posts")
public class PostModel {

    // 投稿ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    // ユーザーID（外部キー）
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // タイトル
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    // 説明
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // 投稿日時
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // コンストラクタ
    public PostModel() {
        this.createdAt = LocalDateTime.now(); // デフォルトで現在時刻を設定
    }

    // ゲッターとセッター
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
