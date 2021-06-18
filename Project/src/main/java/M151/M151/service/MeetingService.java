package M151.M151.service;

import M151.M151.dto.MeetingWithRoom;
import M151.M151.model.*;
import M151.M151.repo.InviteRepo;
import M151.M151.repo.MeetingRepo;
import M151.M151.repo.MeetingRoomRepo;
import M151.M151.repo.UserRepo;
import org.hibernate.query.spi.StreamDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@CacheConfig(cacheNames = {"meetings"})
public class MeetingService {
    private final MeetingRepo meetingRepo;
    private final MeetingRoomRepo meetingRoomRepo;
    private final InviteRepo inviteRepo;
    private final UserService userService;

    @Autowired
    public MeetingService(final MeetingRepo meetingRepo, final MeetingRoomRepo meetingRoomRepo,
                          final UserService userService, final InviteRepo inviteRepo) {
        this.meetingRepo = meetingRepo;
        this.meetingRoomRepo = meetingRoomRepo;
        this.userService = userService;
        this.inviteRepo = inviteRepo;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "0")
    public List<Meeting> getAll() {
        final Iterable<Meeting> meetings = meetingRepo.findAll();

        return StreamSupport
                .stream(meetings.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "0")
    public List<Meeting> getAllMyMeeting() {
        final Iterable<Meeting> meetings = meetingRepo.findAll();
        final List<Meeting> myMeetings = new ArrayList<>();
        final User currentUser = userService.getCurrentUser().get();

        for (Meeting m: meetings) {
            if (currentUser.getUserGroup() == UserGroup.User ||
                    currentUser.getUserGroup() == UserGroup.Admin) {
                for (Invites i: inviteRepo.findAll()) {
                    if (i.getMeeting().getId() == m.getId()) {
                        if (i.getInviter().getId() == currentUser.getId() ||
                        i.getInvited().getId() == currentUser.getId()) {
                            myMeetings.add(m);
                        }
                    }
                }
            }
        }

        return StreamSupport
                .stream(myMeetings.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(key = "#result.id")
    @CacheEvict(key = "0")
    public Meeting add(final MeetingWithRoom meetingWithRoom) {
        final Optional<MeetingRoom> meetingRoom = meetingRoomRepo.findById(meetingWithRoom.getMeetingRoom());
        if (meetingRoom.isPresent()) {
            Meeting meeting = new Meeting(meetingWithRoom.getMeeting().getStartTime(), meetingWithRoom.getMeeting().getEndTime(),
                    meetingWithRoom.getMeeting().getName(), meetingRoom.get());

            return meetingRepo.save(meeting);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<Meeting> get(final long id) {
        return Optional.ofNullable(meetingRepo.findById(id));
    }
}
