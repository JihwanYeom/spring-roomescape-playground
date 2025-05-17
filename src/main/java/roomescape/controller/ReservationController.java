package roomescape.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import roomescape.dto.ReservationDTO;
import roomescape.service.ReservationService;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> readReservations() {
        List<ReservationDTO> reservations = reservationService.readReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO newReservation = reservationService.addReservation(reservationDTO);
        return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId()))
                .body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
