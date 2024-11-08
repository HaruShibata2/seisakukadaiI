package jp.ac.teami.seisakukadaiI.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column
    private String username; // ユーザー名
    
    @Column
    private String email; // メールアドレス
    
    @Column(name = "user_id")  // カラム名を明示的に指定
    private String userId; // ユーザーID（フィールド名を userId に統一）
    
    @Basic(optional = false)
    private String password; // パスワード

    // @Enumerated(EnumType.STRING) を使用してロール管理を行いたい場合
    // private UserRole role; // ユーザーロール

    private Date entryDate; // 登録日
    private String department; // 部署
    
    
    // 投稿リスト（OneToMany のリレーション）
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostModel> posts = new ArrayList<>(); // ユーザーが作成した投稿

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
