package roomescape.domain;

import java.time.LocalTime;

public class Time {
    private final Long id;
    private final LocalTime time;

    public Time(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public static Time of(Long id, Time time) {
        return new Time(id, time.getTime());
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime(){
        return time;
    }

}
