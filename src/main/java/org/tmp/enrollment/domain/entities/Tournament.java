package org.tmp.enrollment.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Tournament {

    private String id;
    private String organizerName;
    private TournamentName name;
    private Location location;
    private TournamentDescription description;
    private Sport sport;
    private Strategy strategy;
    private int capacity;
    private Enrollment enrollment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime backupStartTime;
    private LocalDateTime backupEndTime;
}
