package org.tmp.enrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmp.enrollment.data.TeamRepository;
import org.tmp.enrollment.domain.entities.Team;
import org.tmp.enrollment.util.StreamUtil;

import java.util.Collection;
import java.util.List;

@Component
public class TeamService {

    private final TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> getByIds(Collection<String> ids) {
        return StreamUtil.mapToList(ids, repository::findByName);
    }
}
