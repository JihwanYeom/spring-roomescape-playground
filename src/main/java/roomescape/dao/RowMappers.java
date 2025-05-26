package roomescape.dao;

import org.springframework.jdbc.core.RowMapper;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.exception.InvalidTimeFormatException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RowMappers {


    public static final RowMapper<Time> TIME_MAPPER = (resultSet, rowNum) -> {
        try {
            String timeStr = resultSet.getString("time");
            LocalTime parsedTime = LocalTime.parse(timeStr);
            return new Time(resultSet.getLong("id"), parsedTime);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    };

    public static final RowMapper<Reservation> RESERVATION_MAPPER = (resultSet, rowNum) -> {
        try {
            String timeStr = resultSet.getString("time_value");
            LocalTime parsedTime = LocalTime.parse(timeStr);
            return new Reservation(
                    resultSet.getLong("reservation_id"),
                    resultSet.getString("date"),
                    resultSet.getString("name"),
                    new Time(resultSet.getLong("time_id"), parsedTime)
            );
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    };
}
