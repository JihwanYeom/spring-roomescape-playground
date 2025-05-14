package roomescape.dao;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import roomescape.domain.Reservation;

import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ReservationDAO {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private AtomicLong index = new AtomicLong(0);

    @PostConstruct
    void init() {
        String sql = "create table IF NOT EXISTS reservation (id BIGINT, name varchar(50), date date, time time);";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Reservation reservation) {
        String sql = "insert into reservation values(?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            long generatedId = index.incrementAndGet();
            preparedStatement.setLong(1, generatedId);
            preparedStatement.setString(2, reservation.getName());
            preparedStatement.setString(3, reservation.getDate());
            preparedStatement.setString(4, reservation.getTime());
            preparedStatement.executeUpdate();

            reservation.setId(generatedId);  // 생성된 ID 값을 reservation 객체에 설정
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("date"),
                        resultSet.getString("time")
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }

    public void deleteById(Long id) throws SQLException {
        String sql = "delete from reservation where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
