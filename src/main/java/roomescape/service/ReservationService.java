package roomescape.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.dao.TimeDao;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;

import java.sql.SQLException;
import java.util.List;
import roomescape.exception.DuplicateReservationException;
import roomescape.exception.NotFoundReservationException;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final TimeDao timeDao;

    public ReservationService(ReservationDao reservationDao, TimeDao timeDao) {
        this.reservationDao = reservationDao;
        this.timeDao = timeDao;
    }

    public List<ReservationResponse> findAll() {
        List<Reservation> reservations = reservationDao.findAll();
        List<ReservationResponse> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            result.add(ReservationResponse.from(reservation));
        }
        return result;
    }

    public ReservationResponse create(ReservationRequest reservationRequest) {
        Long timeId = Long.parseLong(reservationRequest.getTime());
        Time time = timeDao.findById(timeId);

        if (reservationDao.reservationIsExist(reservationRequest.getDate(), timeId)) {
            throw new DuplicateReservationException();
        }

        Reservation reservation = new Reservation(
                null,
                reservationRequest.getName(),
                reservationRequest.getDate(),
                time
        );
        Reservation createdReservation = reservationDao.create(reservation);
        return ReservationResponse.from(createdReservation);
    }

    public void deleteById(Long id) {
        validateReservationIdExists(id);
        try {
            reservationDao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateReservationIdExists(Long id) {
        if (!reservationDao.idIsExist(id)) {
            throw new NotFoundReservationException(id);
        }
    }

}
