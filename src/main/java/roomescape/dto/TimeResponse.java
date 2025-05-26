package roomescape.dto;

import java.time.LocalTime;
import roomescape.domain.Time;

public class TimeResponse {

    private final LocalTime time;
    private final Long id;

    public TimeResponse( Long id, LocalTime time) {
        this.id = id;
        this.time = time;

    }

    public static TimeResponse from(Time time) {
        return new TimeResponse(
                time.getId(),
                time.getTime()
        );
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

}
