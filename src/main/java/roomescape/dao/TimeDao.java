package roomescape.dao;

import java.time.LocalTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.domain.Time;

@Repository
public class TimeDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public TimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("time")
                .usingGeneratedKeyColumns("id");
    }

    public List<Time> findAll() {
        String sql = "SELECT * FROM time";

        List<Time> times = jdbcTemplate.query(
                sql, RowMappers.TIME_MAPPER
        );
        return times;
    }

    public Time findById(Long timeId) {
        String sql = "SELECT * FROM time WHERE id = ?";
        Time time = jdbcTemplate.queryForObject(sql, RowMappers.TIME_MAPPER, timeId);
        return time;
    }

    public Time create(Time time) {
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(time);
        Number key = simpleJdbcInsert.executeAndReturnKey(paramSource);

        return Time.withId(key.longValue(), time);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM time WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public boolean idIsExist(Long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM time WHERE id = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
    }

    public boolean timeIsExists(LocalTime time) {
        String sql = "SELECT COUNT(*) FROM time WHERE time = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, time);
        return count != null && count > 0;
    }

}
