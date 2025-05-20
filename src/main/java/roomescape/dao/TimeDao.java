package roomescape.dao;

import java.sql.SQLException;
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
                sql, (resultSet, rowNum) -> new Time(
                        resultSet.getLong("id"),
                        resultSet.getString("time")
                )
        );
        return times;
    }

    public Time create(Time time) {
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(time);
        Number key = simpleJdbcInsert.executeAndReturnKey(paramSource);

        return Time.of(key.longValue(), time);
    }

    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM time WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public boolean idIsExist(Long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM time WHERE id = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
    }

}
