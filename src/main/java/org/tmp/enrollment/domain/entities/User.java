package org.tmp.enrollment.domain.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data @Slf4j
public class User {

    public User(String name) {
        this.name = name;
        this.id = name;
    }

    @Id
    private String id;

    private String name;
    private List<String> organizedTournamentIds = new ArrayList<>();
    private List<String> participatingTournamentIds = new ArrayList<>();


    public void removeOrganizedTournamentId(String id) {
        organizedTournamentIds.remove(id);
    }

    public void removeParticipatingTournamentId(String id) {
        participatingTournamentIds.remove(id);
    }
}
