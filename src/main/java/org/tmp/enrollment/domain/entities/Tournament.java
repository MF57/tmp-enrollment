package org.tmp.enrollment.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Tournament {

    private User organizer;
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
