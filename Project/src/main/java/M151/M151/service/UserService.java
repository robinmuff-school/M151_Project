package M151.M151.service;

import M151.M151.model.User;
import M151.M151.repo.UserRepo;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(final UserRepo userRepo) { this.userRepo = userRepo;}

    @Transactional
    public Optional<User> getCurrentUser() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Optional<User> optionalUser = userRepo.findByUsername(context.getAuthentication().getName());
        return optionalUser;
    }

    @Transactional
    @CachePut(key = "#user.id")
    @CacheEvict(key = "0")
    public User add(final User user) {
        return userRepo.save(user);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public User update(final long id, final User user) {
        final Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            foundUser.setFirstname(user.getFirstname());
            foundUser.setLastname(user.getLastname());
            foundUser.setUsername(user.getUsername());
            foundUser.setUserGroup(user.getUserGroup());
            return userRepo.save(foundUser);
        }
        return null;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(key = "0")})
    public void delete(final long id) {
        final Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            foundUser.setDeleted(true);
            Optional.of(userRepo.save(foundUser));
        }
    }
}
