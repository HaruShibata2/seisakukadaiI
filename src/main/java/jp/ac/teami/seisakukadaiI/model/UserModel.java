package jp.ac.teami.seisakukadaiI.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // ユーザーID

    @Column(unique = true, nullable = false)
    private String username; // ユーザー名（ユニーク制約追加）

    @Column(unique = true, nullable = false)
    private String email; // メールアドレス

    @Column(name = "user_id", unique = true, nullable = false) // ユーザーID（ユニークかつNULL不可に設定）
    private String userId;

    @Basic(optional = false)
    private String password; // パスワード

    private Date entryDate; // 登録日
    private String department; // 部署

    @Enumerated(EnumType.STRING) // データベースに文字列として保存されるように指定
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostModel> posts = new ArrayList<>(); // ユーザーが作成した投稿
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SafetyCheck> check = new ArrayList<>(); 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InquiryModel> inquiry = new ArrayList<>(); 
    
    
    
    // Enumでロールを定義
    public enum UserRole {
        ADMIN, LEADER, GENERAL;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

