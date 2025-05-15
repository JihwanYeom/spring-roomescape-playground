package roomescape.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDAO;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDTO;
import roomescape.dto.ReservationResponseDTO;

import java.sql.SQLException;
import java.util.List;
import roomescape.exception.EmptyDataException;
import roomescape.exception.NotFoundReservationException;

@Service
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservations = reservationDAO.findAll();
        List<ReservationResponseDTO> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            result.add(new ReservationResponseDTO(
                    reservation.getId(),
                    reservation.getName(),
                    reservation.getDate(),
                    reservation.getTime()));
        }
        return result;
    }

    public ReservationResponseDTO create(ReservationRequestDTO reservationDTO) {
        validateEmptyData(reservationDTO);
        Reservation reservation = new Reservation(
                null,
                reservationDTO.getName(),
                reservationDTO.getDate(),
                reservationDTO.getTime()
        );
        reservationDAO.create(reservation);
        ReservationResponseDTO dto = new ReservationResponseDTO(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
        return dto;
    }

    public void deleteById(Long id) {
        validateReservationIdExists(id);
        try {
            reservationDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateEmptyData(ReservationRequestDTO reservation) {
        if(reservation.getDate() == null || reservation.getDate().isEmpty())
            throw new EmptyDataException("날짜 정보가 입력되지 않았습니다");
        if(reservation.getTime() == null || reservation.getTime().isEmpty())
            throw new EmptyDataException("시간 정보가 입력되지 않았습니다");
        if(reservation.getName() == null || reservation.getName().isEmpty())
            throw new EmptyDataException("이름이 입력되지 않았습니다");
    }

    public void validateReservationIdExists(Long id) {
        List<ReservationResponseDTO> reservations = findAll();
        boolean exists = reservations.stream()
                .anyMatch(reservation -> reservation.getId().equals(id));

        if (!exists) {
            throw new NotFoundReservationException("ID " + id + "에 해당하는 예약이 존재하지 않습니다.");
        }
    }
}
