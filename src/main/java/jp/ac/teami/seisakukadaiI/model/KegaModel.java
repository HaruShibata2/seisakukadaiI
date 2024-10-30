package jp.ac.teami.seisakukadaiI.model;
 
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 a
@Entity
@Table(name = "Kega")
public class KegaModel {
 
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
 
    @Id
    @Column(name = "date_of_injury", nullable = false)
    private Date dateOfInjury;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "severity_level", length = 50, nullable = false)
    private SeverityLevel severityLevel;
 
    @Column(name = "injury_type", length = 100)
    private String injuryType;
 
    @Column(name = "location", length = 100)
    private String location;
 
    @Column(name = "treated", nullable = false)
    private Boolean treated = false;
 
    // ゲッターとセッター
 
    public Integer getUserId() {
        return userId;
    }
 
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
 
    public Date getDateOfInjury() {
        return dateOfInjury;
    }
 
    public void setDateOfInjury(Date dateOfInjury) {
        this.dateOfInjury = dateOfInjury;
    }
 
    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }
 
    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
 
    public String getInjuryType() {
        return injuryType;
    }
 
    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }
 
    public String getLocation() {
        return location;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }
 
    public Boolean getTreated() {
        return treated;
    }
 
    public void setTreated(Boolean treated) {
        this.treated = treated;
    }
 
    // Enum for Severity Level
    public enum SeverityLevel {
        MINOR,      // 軽傷
        MAJOR       // 重傷
    }
}