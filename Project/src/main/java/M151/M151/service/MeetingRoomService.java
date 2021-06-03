package M151.M151.service;

import M151.M151.model.MeetingRoom;
import M151.M151.repo.MeetingRepo;
import M151.M151.repo.MeetingRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeetingRoomService {
    private final MeetingRoomRepo meetingRoomRepo;

    @Autowired
    public MeetingRoomService(final MeetingRoomRepo meetingRoomRepo) {
        this.meetingRoomRepo = meetingRoomRepo;
    }

    @Transactional(readOnly = true)
    public List<MeetingRoom> getAll() {
        final Iterable<MeetingRoom> fruits = meetingRoomRepo.findAll();
        return StreamSupport
                .stream(fruits.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public MeetingRoom add(final MeetingRoom meetingRoom) {
        return meetingRoomRepo.save(meetingRoom);
    }

    public void delete(final long id) {
        meetingRoomRepo.deleteById(id);
    }

    public void deleteAll() {
        meetingRoomRepo.deleteAll(meetingRoomRepo.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<MeetingRoom> get(final long id) {
        return Optional.ofNullable(meetingRoomRepo.findById(id));
    }
}
