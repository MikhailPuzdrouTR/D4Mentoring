package com.mp.d4mentroing.module2.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class JpaInstantConverter implements AttributeConverter<Instant, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(Instant attribute) {
        Timestamp timestamp = null;
        if (attribute != null) {
            timestamp = Timestamp.from(attribute);
        }
        return timestamp;
    }

    @Override
    public Instant convertToEntityAttribute(Timestamp dbData) {
        Instant instant = null;
        if (dbData != null) {
            instant = dbData.toInstant();
        }
        return instant;
    }
}
