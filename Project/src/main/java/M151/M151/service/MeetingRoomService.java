package M151.M151.service;

import M151.M151.model.Invites;
import M151.M151.model.Meeting;
import M151.M151.model.MeetingRoom;
import M151.M151.model.UserGroup;
import M151.M151.repo.MeetingRepo;
import M151.M151.repo.MeetingRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@CacheConfig(cacheNames = {"meetingrooms"})
public class MeetingRoomService {
    private final MeetingRoomRepo meetingRoomRepo;
    private final MeetingRepo meetingRepo;

    @Autowired
    public MeetingRoomService(final MeetingRoomRepo meetingRoomRepo, final MeetingRepo meetingRepo) {

        this.meetingRoomRepo = meetingRoomRepo;
        this.meetingRepo = meetingRepo;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "0")
    public List<MeetingRoom> getAll() {
        final Iterable<MeetingRoom> meetingRooms = meetingRoomRepo.findAll();
        return StreamSupport
                .stream(meetingRooms.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Meeting> myMeetings(final Long id) {
        final Iterable<Meeting> meetings = meetingRepo.findAll();
        final List<Meeting> myMeetings = new ArrayList<>();

        for (Meeting m: meetings) {
            if (m.getMeetingRoom().getId() == id) {
                myMeetings.add(m);
            }
        }

        return StreamSupport
                .stream(myMeetings.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(key = "#result.id")
    @CacheEvict(key = "0")
    public MeetingRoom add(final MeetingRoom meetingRoom) {
        return meetingRoomRepo.save(meetingRoom);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<MeetingRoom> get(final long id) {
        return meetingRoomRepo.findById(id);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public Optional<MeetingRoom> update(final long id, final MeetingRoom meetingRoom) {
        final Optional<MeetingRoom> optionalMeeting = meetingRoomRepo.findById(id);
        if (optionalMeeting.isPresent()) {
            MeetingRoom foundMeetingRoom = optionalMeeting.get();
            foundMeetingRoom.setName(meetingRoom.getName());
            return Optional.of(meetingRoomRepo.save(foundMeetingRoom));
        }
        return Optional.empty();
    }
}
