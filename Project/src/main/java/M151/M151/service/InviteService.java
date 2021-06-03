package M151.M151.service;

import M151.M151.model.Invites;
import M151.M151.repo.InviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InviteService {
    private final InviteRepo inviteRepo;

    @Autowired
    public InviteService(final InviteRepo inviteRepo) {
        this.inviteRepo = inviteRepo;
    }

    @Transactional(readOnly = true)
    public List<Invites> getAll() {
        final Iterable<Invites> fruits = inviteRepo.findAll();
        return StreamSupport
                .stream(fruits.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Invites add(final Invites invites) {
        return inviteRepo.save(invites);
    }

    public void delete(final long id) {
        inviteRepo.deleteById(id);
    }

    public void deleteAll() {
        inviteRepo.deleteAll(inviteRepo.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<Invites> get(final long id) {
        return Optional.ofNullable(inviteRepo.findById(id));
    }
}
