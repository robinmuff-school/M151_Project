package M151.M151.service;

import M151.M151.model.User;
import M151.M151.model.UserGroup;
import M151.M151.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {
    private static final String REDIS_KEY = "loginUsers";
    private final UserRepo userRepo;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public LoginService(final UserRepo userRepo, final StringRedisTemplate redisTemplate) {
        this.userRepo = userRepo;
        this.redisTemplate = redisTemplate;
    }

    @Transactional(readOnly = true)
    public Optional<UserGroup> login(final String username, final String userPassword) {
        if (redisTemplate.opsForHash().hasKey(REDIS_KEY, username)) {
            return Optional.of(UserGroup.valueOf(redisTemplate.opsForHash().get(REDIS_KEY, username).toString()));
        }

        final User user = userRepo.checkPassword(username, userPassword);
        if (user != null) {
            redisTemplate.opsForHash().put(REDIS_KEY, user.getUsername(), user.getUserGroup().toString());
            return Optional.of(user.getUserGroup());
        }

        return Optional.empty();
    }

}
