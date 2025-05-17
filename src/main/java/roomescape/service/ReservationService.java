package roomescape.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.domain.Reservations;
import roomescape.dto.ReservationDTO;

import java.util.List;

@Service
public class ReservationService {

    private final Reservations reservations;

    @Autowired
    public ReservationService(Reservations reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDTO> readReservations() {
        List<ReservationDTO> result = new ArrayList<>();
        for (Reservation reservation : reservations.getReservations()) {
            result.add(ReservationDTO.from(reservation));
        }
        return result;
    }

    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(
                reservations.getNewId(),
                reservationDTO.getName(),
                reservationDTO.getDate(),
                reservationDTO.getTime()
        );
        reservations.addReservation(reservation);
        return ReservationDTO.from(reservation);
    }

    public void deleteById(Long id) {
        reservations.deleteReservation(id);
    }

}
