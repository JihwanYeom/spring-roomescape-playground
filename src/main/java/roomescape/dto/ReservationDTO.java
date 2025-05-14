package roomescape.dto;

import roomescape.domain.Reservation;

public class ReservationDTO {

    private Long id;
    private String name;
    private String date;
    private String time;

    public ReservationDTO(Long id, String name, String date, String time) {
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

    public String getTime() {
        return time;
    }

    public static ReservationDTO from(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
    }
}
