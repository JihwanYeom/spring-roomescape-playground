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
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;
import roomescape.exception.EmptyTimeException;
import roomescape.service.TimeService;

@RestController
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/times")
    public ResponseEntity<List<TimeResponse>> readTimes() {
        List<TimeResponse> times = timeService.findAll();
        return ResponseEntity.ok(times);
    }

    @PostMapping("/times")
    public ResponseEntity<TimeResponse> addTime(@RequestBody TimeRequest timeRequest) {
        validateEmptyData(timeRequest);
        TimeResponse newTime = timeService.create(timeRequest);
        return ResponseEntity.created(URI.create("/times")).body(newTime);
    }

    private void validateEmptyData(TimeRequest time) {
        if(time.getTime() == null || time.getTime().isEmpty())
            throw new EmptyTimeException();
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
