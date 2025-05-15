package roomescape.domain;

import roomescape.util.ReservationValidator;

public class Reservation {

    private Long id;
    private String name;
    private String date;
    private String time;

    public Reservation() {

    }


    public Reservation(Long id, Reservation reservation) {
        ReservationValidator.validateEmptyData(reservation);
        this.id = id;
        this.name = reservation.name;
        this.date = reservation.date;
        this.time = reservation.time;
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

}

