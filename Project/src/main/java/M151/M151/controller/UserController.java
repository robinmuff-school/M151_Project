package M151.M151.controller;

import M151.M151.model.User;
import M151.M151.model.UserGroup;
import M151.M151.repo.UserRepo;
import M151.M151.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/users")
@PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired
    public UserController(final UserRepo userRepo, final UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/info")
    public Authentication getInfo() {
        final SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public List<User> getAll() {
        final Iterable<User> users = userRepo.getActiveUser();
        return StreamSupport
                .stream(users.spliterator(), false)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('Admin')")
    public User add(@RequestBody final User user) {
        return userService.add(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public void delete(@PathVariable final long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public User update(@PathVariable final long id, @RequestBody final User user) {
        System.out.println("Hallo");
        return userService.update(id, user);
    }
}
