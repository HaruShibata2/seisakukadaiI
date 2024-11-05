package jp.ac.teami.seisakukadaiI.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
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

    private String username; // ユーザー名
    private String email; // メールアドレス

    @Basic(optional = false)
    private String password; // パスワード

//    @Enumerated(EnumType.STRING)
//    private UserRole role; // ユーザーロール

    private Date entryDate; // 登録日

    private String department; // 部署

    // デフォルトコンストラクタでロールを設定
//    public UserModel() {
//        this.role = UserRole.GENERAL; // デフォルトロールを設定
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
//        if (role != null) {
//            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
//        }
        return authorityList; // roleがnullの場合は空のリストを返す
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEntryDate() {
        return entryDate;
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

    // Enum for Role field
//    public enum UserRole {
//        ADMIN,     // 管理者
//        LEADER,    // リーダー
//        GENERAL;   // 一般ユーザー
//
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        }
//    }
}
