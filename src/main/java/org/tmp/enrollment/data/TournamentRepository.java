package org.tmp.enrollment.data;

import org.springframework.data.repository.CrudRepository;
import org.tmp.enrollment.domain.entities.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, String> {
    Tournament findById(String id);
}
