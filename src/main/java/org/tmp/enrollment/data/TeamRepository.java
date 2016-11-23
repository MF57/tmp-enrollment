package org.tmp.enrollment.data;

import org.springframework.data.repository.CrudRepository;
import org.tmp.enrollment.domain.entities.Team;

public interface TeamRepository extends CrudRepository<Team, String> {
    Team findByName(String teamName);
}
