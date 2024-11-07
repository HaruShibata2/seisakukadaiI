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
    private String userId; // ユーザーID（フィールド名が userId）
    
    @Basic(optional = false)
    private String password; // パスワード

    // @Enumerated(EnumType.STRING) を使用してロール管理を行いたい場合
    // private UserRole role; // ユーザーロール

    private Date entryDate; // 登録日
    private String department; // 部署

    // デフォルトコンストラクタでロールを設定（ロール管理を復活したい場合）
    // public UserModel() {
    //    this.role = UserRole.GENERAL; // デフォルトロールを設定
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        // roleがnullでない場合にロールを権限に追加する
        // if (role != null) {
        //     authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        // }
        return authorityList; // roleがnullの場合は空のリストを返す
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
    public String getUserId() {
        return this.userId; // ユーザーIDを返す
    }

    // ユーザーIDを設定
    public void setUserId(String userId) {
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

    // Enum for Role field（ロールを管理したい場合はここを復活）
    // public enum UserRole {
    //    ADMIN,     // 管理者
    //    LEADER,    // リーダー
    //    GENERAL;   // 一般ユーザー
    //    public Collection<? extends GrantedAuthority> getAuthorities() {
    //        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
    //    }
    // }
}
