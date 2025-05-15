package roomescape.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;
import roomescape.util.ReservationValidator;

@Component("reservations")
public class Reservations {
    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(0);

    public Long getNewId() {
        return index.incrementAndGet();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void deleteReservation(Long id) {
        ReservationValidator.validateReservationIdExists(reservations, id);
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
