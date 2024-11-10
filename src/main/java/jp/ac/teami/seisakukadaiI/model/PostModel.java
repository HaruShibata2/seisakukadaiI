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
@Table(name = "posts")
public class PostModel {

    // 投稿ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel user;

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



    @Override
    public String toString() {
        return "PostModel{" +
                "postId=" + postId +
                ", user=" + user.getUserId() + // UserModelのuserIdを表示
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
