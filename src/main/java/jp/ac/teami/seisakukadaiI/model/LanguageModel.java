package jp.ac.teami.seisakukadaiI.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 a
@Entity
@Table(name = "gengo")
public class LanguageModel {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;
 
    @Column(name = "language_code", length = 2, nullable = false)
    private String languageCode;
 
    @Column(name = "language_name", length = 255, nullable = false)
    private String languageName;
 
    @Column(name = "locale_code", length = 5, nullable = false)
    private String localeCode;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "text_direction", nullable = false, columnDefinition = "ENUM('LTR', 'RTL') DEFAULT 'LTR'")
    private TextDirection textDirection = TextDirection.LTR;
 
    @Column(name = "flag_url", length = 255)
    private String flagUrl;
 
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
 
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
 
    // ゲッターとセッター
 
    public Integer getLanguageId() {
        return languageId;
    }
 
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }
 
    public String getLanguageCode() {
        return languageCode;
    }
 
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
 
    public String getLanguageName() {
        return languageName;
    }
 
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
 
    public String getLocaleCode() {
        return localeCode;
    }
 
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
 
    public TextDirection getTextDirection() {
        return textDirection;
    }
 
    public void setTextDirection(TextDirection textDirection) {
        this.textDirection = textDirection;
    }
 
    public String getFlagUrl() {
        return flagUrl;
    }
 
    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
 
    public Boolean getIsDefault() {
        return isDefault;
    }
 
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
 
    public Boolean getIsActive() {
        return isActive;
    }
 
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
 
    // Enum for text direction
    public enum TextDirection {
        LTR,    // 左から右
        RTL     // 右から左
    }
}