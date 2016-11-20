package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tmp.enrollment.data.TournamentRepository;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.User;
import org.tmp.enrollment.domain.validations.TournamentChangeValidation;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;
import org.tmp.enrollment.util.StreamUtil;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserService userService;
    private final TournamentChangeValidation tournamentValidator;

    @Autowired
    public TournamentService(TournamentRepository repository, UserService userService, TournamentChangeValidation validation) {
        this.tournamentRepository = repository;
        this.userService = userService;
        this.tournamentValidator = validation;
    }

    public Tournament getById(String id) {
        return tournamentRepository.findById(id);
    }

    public Tournament save(Tournament tournament) {
        if(isNewTournament(tournament)) {
            generateAndSetID(tournament);
            updateOrganizer(tournament);
        }
        updateParticipants(tournament);

        return tournamentRepository.save(tournament);
    }

    public Tournament update(Tournament updatedTournament) throws TournamentModificationError {
        String tournamentId = updatedTournament.getId();
        Tournament original = tournamentRepository.findById(tournamentId);
        tournamentValidator.validate(original, updatedTournament);
        return tournamentRepository.save(updatedTournament);
    }

    private void updateParticipants(Tournament tournament) {
        if(tournament.getEnrollment() != null) {
            tournament.getEnrollment().getEnrolledParticipantNames().stream()
                    .forEach(participantName -> updateParticipant(participantName, tournament));
        }
    }

    private void generateAndSetID(Tournament tournament) {
        tournament.setId(UUID.randomUUID().toString());
    }

    private void updateOrganizer(Tournament tournament) {
        User organizer = userService.findUser(tournament.getOrganizerName());
        organizer.getOrganizedTournamentIds().add(tournament.getId());
        userService.saveUser(organizer);
    }

    private void updateParticipant(String participantName, Tournament tournament) {
        User participant = userService.findUser(participantName);
        participant.getParticipatingTournamentIds().add(tournament.getId());
        userService.saveUser(participant);
    }

    public List<Tournament> getAllByIds(Collection<String> ids) {
        return StreamUtil.mapToList(ids, this::getById);
    }

    private boolean isNewTournament(Tournament tournament) {
        return StringUtils.isEmpty(tournament.getId());
    }
}
