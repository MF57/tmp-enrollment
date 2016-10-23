package org.tmp.enrollment.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String name;
    private List<Long> organizedTournamentIds;
    private List<Long> participatingTournamentIds;
}
