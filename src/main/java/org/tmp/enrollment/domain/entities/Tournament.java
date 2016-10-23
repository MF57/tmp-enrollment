package org.tmp.enrollment.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Tournament {

    private Long tournamentId;
    private String organizerName;
    private TournamentName tournamentName;
    private Location location;
    private TournamentDescription tournamentDescription;
    private Sport sport;
    private Strategy strategy;
    private int capacity;
    private Enrollment enrollment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime backupStartTime;
    private LocalDateTime backupEndTime;
}
