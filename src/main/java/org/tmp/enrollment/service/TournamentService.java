package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tmp.enrollment.data.TournamentRepository;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.util.StreamUtil;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository repository) {
        this.tournamentRepository = repository;
    }

    public Tournament getById(Long id) {
        Tournament tournament = new Tournament();
        tournament.setId("1");
        tournament.setOrganizerName("Janek");
        return tournament;
    }

    public void save(Tournament tournament) {
        if(isNewTournament(tournament)) {
            tournament.setId(UUID.randomUUID().toString());
        }
        tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllByIds(Collection<Long> ids) {
        return StreamUtil.mapToList(ids, this::getById);
    }

    private boolean isNewTournament(Tournament tournament) {
        return StringUtils.isEmpty(tournament.getId());
    }
}
