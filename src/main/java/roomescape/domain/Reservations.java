package roomescape.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component("reservations")
public class Reservations {
    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(0);
    Validator validator = new Validator();

    public Long getNewId() {
        return index.incrementAndGet();
    }

    public void addReservation(Reservation reservation) {
        validator.validateEmptyData(reservation);
        reservations.add(reservation);
    }

    public void deleteReservation(Long id) {
        validator.validateReservationIdExists(getReservations(),id);
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
