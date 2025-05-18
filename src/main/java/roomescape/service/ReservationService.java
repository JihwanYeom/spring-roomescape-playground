package roomescape.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;

import java.sql.SQLException;
import java.util.List;
import roomescape.exception.EmptyDataException;
import roomescape.exception.NotFoundReservationException;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public List<ReservationResponse> findAll() {
        List<Reservation> reservations = reservationDao.findAll();
        List<ReservationResponse> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            result.add(ReservationResponse.from(reservation));
        }
        return result;
    }

    public ReservationResponse create(ReservationRequest reservationDTO) {
        validateEmptyData(reservationDTO);
        Reservation reservation = new Reservation(
                null,
                reservationDTO.getName(),
                reservationDTO.getDate(),
                reservationDTO.getTime()
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

    private void validateEmptyData(ReservationRequest reservation) {
        if(reservation.getDate() == null || reservation.getDate().isEmpty())
            throw new EmptyDataException("날짜 정보가 입력되지 않았습니다");
        if(reservation.getTime() == null || reservation.getTime().isEmpty())
            throw new EmptyDataException("시간 정보가 입력되지 않았습니다");
        if(reservation.getName() == null || reservation.getName().isEmpty())
            throw new EmptyDataException("이름이 입력되지 않았습니다");
    }

    private void validateReservationIdExists(Long id) {
        if (!reservationDao.idIsExist(id)) {
            throw new NotFoundReservationException("ID " + id + "에 해당하는 예약이 존재하지 않습니다.");
        }
    }

}
