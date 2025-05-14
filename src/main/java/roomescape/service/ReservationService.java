package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDAO;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public List<ReservationDTO> findAll() {
        return reservationDAO.findAll().stream()
                .map(reservation -> new ReservationDTO(
                        reservation.getId(),
                        reservation.getName(),
                        reservation.getDate(),
                        reservation.getTime()))
                .collect(Collectors.toList());
    }

    public ReservationDTO create(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(
                reservationDTO.getId(),
                reservationDTO.getName(),
                reservationDTO.getDate(),
                reservationDTO.getTime()
        );
        reservationDAO.create(reservation);
        return new ReservationDTO(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
    }

    public void deleteById(Long id) {
        try {
            reservationDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
