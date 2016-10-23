package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmp.enrollment.controller.dtos.UserTournaments;
import org.tmp.enrollment.data.TournamentRepository;
import org.tmp.enrollment.data.UserRepository;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public UserService(UserRepository userRepository, TournamentRepository tournamentRepository) {
        this.userRepository = userRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public Optional<User> findUser(String name) {
        return Optional.ofNullable(userRepository.findByName(name));
    }

    public UserTournaments getTournamentsForUser(String userName) {
        List<Tournament> userParticipatedTournaments = userRepository.findByName(userName)
                .getParticipatingTournamentIds().stream()
                .map(tournamentRepository::findById)
                .collect(Collectors.toList());

        List<Tournament> userOrganizedTournaments = userRepository.findByName(userName)
                .getOrganizedTournamentIds().stream()
                .map(tournamentRepository::findById)
                .collect(Collectors.toList());

        return new UserTournaments(userName, userOrganizedTournaments, userParticipatedTournaments);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
