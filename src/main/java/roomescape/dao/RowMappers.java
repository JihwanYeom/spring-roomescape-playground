package roomescape.dao;

import org.springframework.jdbc.core.RowMapper;
import roomescape.domain.Time;
import roomescape.domain.Reservation;

public class RowMappers {
    public static final RowMapper<Time> TIME_MAPPER = (resultSet, rowNum) -> new Time(
            resultSet.getLong("id"),
            resultSet.getString("time")
    );

    public static final RowMapper<Reservation> RESERVATION_MAPPER = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("reservation_id"),
            resultSet.getString("name"),
            resultSet.getString("date"),
            new Time(resultSet.getLong("time_id"), resultSet.getString("time_value"))
    );
}
