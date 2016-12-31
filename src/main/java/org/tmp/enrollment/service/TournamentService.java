package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tmp.enrollment.controller.error.TournamentException;
import org.tmp.enrollment.data.TournamentRepository;
import org.tmp.enrollment.domain.entities.Enrollment;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.User;
import org.tmp.enrollment.domain.filters.TournamentFilters;
import org.tmp.enrollment.domain.validations.TournamentEditionAction;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;
import org.tmp.enrollment.util.StreamUtil;

import java.util.*;

@Component
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserService userService;
    private final TournamentEditionAction enrollmentOpener;
    private final TournamentFilters filters;

    @Autowired
    public TournamentService(TournamentRepository repository, UserService userService,
                             TournamentEditionAction action, TournamentFilters filters) {
        this.tournamentRepository = repository;
        this.userService = userService;
        this.enrollmentOpener = action;
        this.filters = filters;
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
        enrollmentOpener.performAction(original, updatedTournament);
        return tournamentRepository.save(updatedTournament);
    }

    public void delete(String tournamentId) {
        tournamentRepository.delete(tournamentId);
    }

    public void enrollUserForTournament(Tournament tournament, String userName) throws TournamentException {
        List<String> enrolledParticipantIds = tournament.getEnrollment().getEnrolledParticipantIds();
        if (tournament.getCapacity().getParticipantCount() == enrolledParticipantIds.size()) {
            throw new TournamentException("Maximum capacity of tournament reached");
        }

        if (enrolledParticipantIds.contains(userName)) {
            throw new TournamentException("User already enrolled to this enrollment");
        }

        enrolledParticipantIds.add(userName);
        updateParticipant(userName, tournament);
        tournamentRepository.save(tournament);
    }

    private void updateParticipants(Tournament tournament) {
        Optional.ofNullable(tournament)
                .map(Tournament::getEnrollment)
                .map(Enrollment::getEnrolledParticipantIds)
                .orElse(Collections.emptyList())
                .forEach(participantName -> updateParticipant(participantName, tournament));
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

    public List<Tournament> getEnrollableFor(String userName) {
        return StreamUtil.filter(tournamentRepository.findAll(), filters.enrollableFor(userName));
    }


}
