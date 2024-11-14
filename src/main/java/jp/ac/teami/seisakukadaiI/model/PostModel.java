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

    // UserModelとの関連
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")  // ここでuser_idを使用
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserModel user;

    // タイトル
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    // 説明
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // 投稿日時
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
    
    // LocalDateTimeを格納するフィールド
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    
//    public String getFormattedCreatedAt() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        return createdAt.format(formatter);
//    }

//
    // フォーマットされた作成日時を格納するためのフィールド
//    @Column(name = "formatted_created_at", nullable = false)
//    private String formattedCreatedAt;

    // setterとgetter
//    public String getFormattedCreatedAt() {
//        return formattedCreatedAt;
//    }

//    public void setFormattedCreatedAt(LocalDateTime createdAt) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        this.formattedCreatedAt = createdAt.format(formatter);  // フォーマットされた日時をセット
//    }


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
