package roomescape.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.exception.EmptyDateException;
import roomescape.exception.EmptyNameException;
import roomescape.exception.EmptyTimeException;
import roomescape.service.ReservationService;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> readReservations() {
        List<ReservationResponse> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        validateEmptyData(reservationRequest);
        ReservationResponse newReservation = reservationService.create(reservationRequest);
        return ResponseEntity.created(URI.create("/reservations")).body(newReservation);
    }

    private void validateEmptyData(ReservationRequest reservation) {
        if(reservation.getDate() == null || reservation.getDate().isEmpty())
            throw new EmptyDateException();
        if(reservation.getTimeId() == null)
            throw new EmptyTimeException();
        if(reservation.getName() == null || reservation.getName().isEmpty())
            throw new EmptyNameException();
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
