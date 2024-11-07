package jp.ac.teami.seisakukadaiI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyModel {

    @Id
    @Column(name = "comp_id")
    private long id;  // 企業ID（主キー）

    @Column(name = "name", length = 100)
    private String name;  // 企業名

    @Column(name = "address", length = 255)
    private String address;  // 住所

    // コンストラクタ
    public CompanyModel() {
    }

    public CompanyModel(Integer compId, String name, String address) {
        this.id = compId;
        this.name = name;
        this.address = address;
    }

    // ゲッターとセッター
    public long getCompId() {
        return id;
    }

    public void setCompId(Integer compId) {
        this.id = compId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
                "compId=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
