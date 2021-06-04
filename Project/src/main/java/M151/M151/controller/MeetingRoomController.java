package M151.M151.controller;

import M151.M151.model.MeetingRoom;
import M151.M151.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/meetingrooms")
@PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Meetingroom')")
public class MeetingRoomController {
    private final MeetingRoomService meetingRoomService;

    @Autowired
    public MeetingRoomController(final MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping("/")
    public List<MeetingRoom> getAll() {
        return meetingRoomService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MeetingRoom> get(@PathVariable final long id) {
        return meetingRoomService.get(id);
    }

    @PostMapping("/")
    public MeetingRoom add(@RequestBody final MeetingRoom meetingRoom) {
        return meetingRoomService.add(meetingRoom);
    }
}
