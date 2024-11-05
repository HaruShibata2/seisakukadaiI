//package jp.ac.teami.seisakukadaiI.model;
//
//import java.sql.Date;
//
//import org.springframework.data.annotation.Id;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "users") // データベースのテーブル名
//public class User {
//
//    public enum UserRole {
//        GENERAL, ADMIN // 役割の列挙型
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // ユーザーID
//
//    private String name; // 名前
//
//    @Column(unique = true) // メールアドレスはユニーク
//    private String email; // メールアドレス
//
//    private String password; // パスワード（ハッシュ化されたもの）
//
//    @Enumerated(EnumType.STRING)
//    private UserRole role; // 役割
//
//    private Date entryDate; // 登録日
//
//    private String department; // 部署
//
//    // ゲッターとセッター
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public UserRole getRole() {
//        return role;
//    }
//
//    public void setRole(UserRole role) {
//        this.role = role;
//    }
//
//    public Date getEntryDate() {
//        return entryDate;
//    }
//
//    public void setEntryDate(Date entryDate) {
//        this.entryDate = entryDate;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//}
