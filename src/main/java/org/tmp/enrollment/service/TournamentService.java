package org.tmp.enrollment.service;

import org.springframework.stereotype.Component;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.util.StreamUtil;

import java.util.Collection;
import java.util.List;

@Component
public class TournamentService {

    public Tournament getById(Long id) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(1L);
        tournament.setOrganizerName("Janek");
        return tournament;
    }

    public List<Tournament> getAllByIds(Collection<Long> ids) {
        return StreamUtil.mapToList(ids, this::getById);
    }
}
