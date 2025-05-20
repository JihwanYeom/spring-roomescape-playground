package roomescape.domain;

public class Time {
    private final Long id;
    private final String time;

    public Time(Long id, String time) {
        this.id = id;
        this.time = time;
    }

    public static Time of(Long id, Time time) {
        return new Time(id, time.getTime());
    }

    public Long getId() {
        return id;
    }

    public String getTime(){
        return time;
    }

}
