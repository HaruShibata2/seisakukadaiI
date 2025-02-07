package jp.ac.teami.seisakukadaiI.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stockpile") // テーブル名を明示的に指定
@Data
public class StockpileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自動インクリメントを使用
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
