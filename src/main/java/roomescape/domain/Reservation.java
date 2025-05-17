package roomescape.domain;

import roomescape.util.ReservationValidator;

public class Reservation {

    private Long id;
    private String name;
    private String date;
    private String time;

    public Reservation(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        ReservationValidator.validateEmptyData(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        if(id == null){
            return 0;
        }
        return id.hashCode();
    }
}

