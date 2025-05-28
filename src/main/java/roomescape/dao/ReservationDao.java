package roomescape.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;

@Repository
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    public List<Reservation> findAll() {
        String sql = "SELECT r.id as reservation_id, "
                + "r.name, "
                + "r.date, "
                + "t.id as time_id, "
                + "t.time as time_value "
                + "FROM reservation as r inner join time as t on r.time_id = t.id";

        List<Reservation> reservations = jdbcTemplate.query(
                sql, RowMappers.RESERVATION_MAPPER);
        return reservations;
    }

    public Reservation create(Reservation reservation) {
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(reservation);
        Number key = simpleJdbcInsert.executeAndReturnKey(paramSource);
        return Reservation.of(key.longValue(), reservation);
    }

    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public boolean idIsExist(Long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM reservation WHERE id = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
    }

    public boolean reservationIsExist(String date, Long timeId) {
        String sql = "SELECT COUNT(*) FROM reservation WHERE date = ? AND time_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, date, timeId);
        return count != null && count > 0;
    }

}
