package org.tmp.enrollment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmp.enrollment.controller.dtos.UserTournaments;
import org.tmp.enrollment.data.TournamentRepository;
import org.tmp.enrollment.data.UserRepository;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component @Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public UserService(UserRepository userRepository, TournamentRepository tournamentRepository) {
        this.userRepository = userRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public User findUser(String name) {
        User byName = userRepository.findByName(name);
        if(byName == null) log.info("User {} not found", name);
        return Optional.ofNullable(byName)
                .orElseGet(() -> userRepository.save(new User(name)));
    }

    public UserTournaments getTournamentsForUser(String userName) {
        List<Tournament> userParticipatedTournaments = Optional.ofNullable(findUser(userName).getParticipatingTournamentIds())
                .map(this::getTournamebts)
                .orElse(Collections.emptyList());


        List<Tournament> userOrganizedTournaments = Optional.ofNullable(findUser(userName).getOrganizedTournamentIds())
                .map(this::getTournamebts)
                .orElse(Collections.emptyList());

        return new UserTournaments(userName, userOrganizedTournaments, userParticipatedTournaments);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    private List<Tournament> getTournamebts(List<String> ids) {
        return ids.stream()
                .map(tournamentRepository::findById)
                .collect(Collectors.toList());
    }
}
