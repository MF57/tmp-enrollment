package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmp.enrollment.data.UserRepository;
import org.tmp.enrollment.domain.entities.User;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String name) {
        return Optional.ofNullable(userRepository.findByName(name));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
