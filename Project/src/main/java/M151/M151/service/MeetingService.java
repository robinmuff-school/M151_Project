package M151.M151.service;

import M151.M151.model.Meeting;
import M151.M151.repo.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MeetingService {
    private final MeetingRepo meetingRepo;

    @Autowired
    public MeetingService(final MeetingRepo meetingRepo) {
        this.meetingRepo = meetingRepo;
    }

    @Transactional(readOnly = true)
    public List<Meeting> getAll() {
        final Iterable<Meeting> fruits = meetingRepo.findAll();
        return StreamSupport
                .stream(fruits.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Meeting add(final Meeting meeting) {
        return meetingRepo.save(meeting);
    }

    public void delete(final long id) {
        meetingRepo.deleteById(id);
    }

    public void deleteAll() {
        meetingRepo.deleteAll(meetingRepo.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<Meeting> get(final long id) {
        return Optional.ofNullable(meetingRepo.findById(id));
    }
}
