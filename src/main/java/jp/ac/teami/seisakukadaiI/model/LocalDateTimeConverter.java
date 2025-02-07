package jp.ac.teami.seisakukadaiI.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        // nullをチェックして、nullでない場合にLocalDateTimeをTimestampに変換
        return (attribute == null ? null : Timestamp.valueOf(attribute));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        // nullをチェックして、nullでない場合にTimestampをLocalDateTimeに変換
        return (dbData == null ? null : dbData.toLocalDateTime());
    }
}
