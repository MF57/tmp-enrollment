package org.tmp.enrollment.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class Tournament {

    private Long tournamentId;
    private String organizerName;
    private TournamentName tournamentName;
    private Location location;
    private TournamentDescription tournamentDescription;
    private Sport sport;
    private int capacity;
    private Enrollment enrollment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime backupDate;
    private List<String> participantNames;
}
