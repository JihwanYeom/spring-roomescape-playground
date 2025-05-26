package roomescape.dao;

import org.springframework.jdbc.core.RowMapper;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.exception.InvalidTimeFormatException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RowMappers {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static final RowMapper<Time> TIME_MAPPER = (resultSet, rowNum) -> {
        try {
            String timeStr = resultSet.getString("time");
            LocalTime parsedTime = LocalTime.parse(timeStr, TIME_FORMATTER);
            return new Time(resultSet.getLong("id"), parsedTime);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("올바르지 않은 시간 형식입니다");
        }
    };

    public static final RowMapper<Reservation> RESERVATION_MAPPER = (resultSet, rowNum) -> {
        try {
            String timeStr = resultSet.getString("time_value");
            LocalTime parsedTime = LocalTime.parse(timeStr, TIME_FORMATTER);
            return new Reservation(
                    resultSet.getLong("reservation_id"),
                    resultSet.getString("name"),
                    resultSet.getString("date"),
                    new Time(resultSet.getLong("time_id"), parsedTime)
            );
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("올바르지 않은 시간 형식입니다");
        }
    };
}
