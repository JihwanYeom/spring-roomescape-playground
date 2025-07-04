package roomescape.dto;

import java.time.LocalTime;
import roomescape.domain.Reservation;

public class ReservationResponse {

    private final Long id;
    private final String name;
    private final String date;
    private final LocalTime time;

    public ReservationResponse(Long id, String name, String date, LocalTime time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime().getTime()
        );
    }

}
