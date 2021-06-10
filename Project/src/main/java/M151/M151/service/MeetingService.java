package M151.M151.service;

import M151.M151.dto.MeetingWithRoom;
import M151.M151.model.Meeting;
import M151.M151.model.MeetingRoom;
import M151.M151.repo.MeetingRepo;
import M151.M151.repo.MeetingRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeetingService {
    private final MeetingRepo meetingRepo;
    private final MeetingRoomRepo meetingRoomRepo;

    @Autowired
    public MeetingService(final MeetingRepo meetingRepo, final MeetingRoomRepo meetingRoomRepo) {
        this.meetingRepo = meetingRepo;
        this.meetingRoomRepo = meetingRoomRepo;
    }

    @Transactional(readOnly = true)
    public List<Meeting> getAll() {
        final Iterable<Meeting> meetings = meetingRepo.findAll();

        return StreamSupport
                .stream(meetings.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Meeting add(final MeetingWithRoom meetingWithRoom) {
        final Optional<MeetingRoom> meetingRoom = Optional.ofNullable(meetingRoomRepo.findById(meetingWithRoom.getMeetingRoom()));
        if (meetingRoom.isPresent()) {
            Meeting meeting = new Meeting(meetingWithRoom.getMeeting().getStartTime(), meetingWithRoom.getMeeting().getEndTime(),
                    meetingWithRoom.getMeeting().getName(), meetingRoom.get());

            return meetingRepo.save(meeting);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Optional<Meeting> get(final long id) {
        return Optional.ofNullable(meetingRepo.findById(id));
    }
}
