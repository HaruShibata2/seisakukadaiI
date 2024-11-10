package jp.ac.teami.seisakukadaiI.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // ユーザーID

    @Column
    private String username; // ユーザー名
    
    @Column
    private String email; // メールアドレス
    
    @Column(name = "user_id")  // カラム名を明示的に指定
    private String userId; // ユーザーID（フィールド名を userId に統一）
    
    @Basic(optional = false)
    private String password; // パスワード


    private Date entryDate; // 登録日
    private String department; // 部署
    
    // UserRoleを定義して、ユーザーの役割（role）を管理
    @Enumerated(EnumType.STRING) // データベースに文字列として保存されるように指定
    @Column(nullable = false)
    private UserRole role; // デフォルトは一般ユーザー

    // Enumでロールを定義
    public enum UserRole {
        ADMIN,     // 管理者
        LEADER,    // リーダー
        GENERAL;   // 一般ユーザー

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
        }
    }
    
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ユーザーのロールに基づいて権限を返す
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.name())); // ロールを権限に変換
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username; // ユーザー名としてusernameを返す
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // アカウントが期限切れでないことを示す
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // アカウントがロックされていないことを示す
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 認証情報が期限切れでないことを示す
    }

    @Override
    public boolean isEnabled() {
        return true; // アカウントが有効であることを示す
    }

    @Override
    public String getPassword() {
        return this.password; // パスワードを返す
    }

    // ユーザーIDを取得
    // getUseridはUserDetailsインターフェースに存在しないため削除
    // 必要であれば独自のメソッドとして残すことはできます
    public String getUser_id() {
        return this.userId; // ユーザーIDを返す
    }

    // ユーザーIDを設定
    public void setUser_id(String userId) {
        this.userId = userId; // ユーザーIDを設定
    }

    // 登録日を設定
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    // 部署を取得
    public String getDepartment() {
        return department;
    }

    // 部署を設定
    public void setDepartment(String department) {
        this.department = department;
    }
    

    // ロールを設定
    public void setRole(UserRole role) {
        this.role = role;
    }
}
