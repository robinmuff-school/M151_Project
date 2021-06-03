package M151.M151.controller;


import M151.M151.model.Invites;
import M151.M151.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/invites")
@PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Meetingroom')")
public class InviteController {
    private final InviteService inviteService;

    @Autowired
    public InviteController(final InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @GetMapping("/")
    public List<Invites> getAll() {
        return inviteService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Invites> get(@PathVariable final long id) {
        return inviteService.get(id);
    }

    @PostMapping("/")
    public Invites add(@RequestBody final Invites invites) {
        return inviteService.add(invites);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        inviteService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        inviteService.deleteAll();
    }
}
