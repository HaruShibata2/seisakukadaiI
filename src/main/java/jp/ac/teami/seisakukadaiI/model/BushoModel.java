package jp.ac.teami.seisakukadaiI.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity

@Table(name = "busho")

public class BushoModel {
 
    @Id

    @Column(name = "busho_id")

    private Integer bushoId;  // 部署の識別子（主キー）
 
    @Column(name = "comp_id", nullable = false)

    private Integer compId;  // 企業の識別子
 
    @Column(name = "user_id", nullable = false)

    private String user_id;  // ユーザーの識別子
 
    // コンストラクタ

    public BushoModel() {

    }
 
    public BushoModel(Integer bushoId, Integer compId, String userId) {

        this.bushoId = bushoId;

        this.compId = compId;

        this.user_id = userId;

    }
 
    // ゲッターとセッター

    public Integer getBushoId() {

        return bushoId;

    }
 
    public void setBushoId(Integer bushoId) {

        this.bushoId = bushoId;

    }
 
    public Integer getCompId() {

        return compId;

    }
 
    public void setCompId(Integer compId) {

        this.compId = compId;

    }
 
    public String getUserId() {

        return user_id;

    }
 
    public void setUserId(String userId) {

        this.user_id = userId;

    }
 
    @Override

    public String toString() {

        return "BushoModel{" +

                "bushoId=" + bushoId +

                ", compId=" + compId +

                ", userId=" + user_id +

                '}';

    }

}


 