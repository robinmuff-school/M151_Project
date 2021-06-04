package M151.M151.controller;

import M151.M151.dto.MeetingWithRoom;
import M151.M151.model.Meeting;
import M151.M151.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/meetings")
@PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Meetingroom')")
public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(final MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/")
    public List<Meeting> getAll() {
        return meetingService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Meeting> get(@PathVariable final long id) {
        return meetingService.get(id);
    }

    @PostMapping("/")
    public Meeting add(@RequestBody final MeetingWithRoom meetingWithRoom) {
        return meetingService.add(meetingWithRoom);
    }
}
