package com.tylerbarton.msscbeerservice.web.mappers;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Converts between Timestamp and OffsetDateTime
 */
@Component // Required to be picked up
public class DateMapper {
    /**
     * Converts to OffsetDatetime
     * @param ts Timestamp from an enetity
     * @return OffsetDateTime version of the input Timestamp
     */
    public OffsetDateTime asOffsetDateTime(Timestamp ts){
        if (ts != null){
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    /**
     * Converts to a Timestamp
     * @param offsetDateTime OffsetDateTime to convert
     * @return Timestamp version of input offsetDateTime
     */
    public Timestamp asTimestamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
