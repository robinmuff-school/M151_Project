package M151.M151.service;

import M151.M151.dto.InviteWithUserMeeting;
import M151.M151.model.*;
import M151.M151.repo.InviteRepo;
import M151.M151.repo.MeetingRepo;
import M151.M151.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@CacheConfig(cacheNames = {"invites"})
public class InviteService {
    private final UserService userService;
    private final InviteRepo inviteRepo;
    private final UserRepo userRepo;
    private final MeetingRepo meetingRepo;

    @Autowired
    public InviteService(UserService userService, InviteRepo inviteRepo, UserRepo userRepo, MeetingRepo meetingRepo) {
        this.userService = userService;
        this.inviteRepo = inviteRepo;
        this.userRepo = userRepo;
        this.meetingRepo = meetingRepo;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "0")
    public List<Invites> getAll() {
        final Iterable<Invites> invites = inviteRepo.findAll();

        return StreamSupport
                .stream(invites.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(key = "#result.inviteId")
    @CacheEvict(key = "0")
    public Invites add(final InviteWithUserMeeting inviteWithUserMeeting) {
        final Optional<User> user = userRepo.findById(inviteWithUserMeeting.getUser());
        final Optional<Meeting> meeting = Optional.ofNullable(meetingRepo.findById(inviteWithUserMeeting.getMeeting()));
        if (user.isPresent() && meeting.isPresent()) {
            Invites invite = new Invites(MeetingStatus.Invited, userService.getCurrentUser().get(), user.get(), meeting.get());
            return inviteRepo.save(invite);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<Invites> get(final long id) {
        return inviteRepo.findById(id);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public Optional<Invites> update(final long id, final Invites invites) {
        final Optional<Invites> optionalInvites = inviteRepo.findById(id);
        if (optionalInvites.isPresent()) {
            Invites foundInvites = optionalInvites.get();
            foundInvites.setStatus(invites.getStatus());
            return Optional.of(inviteRepo.save(foundInvites));
        }
        return Optional.empty();
    }
}
