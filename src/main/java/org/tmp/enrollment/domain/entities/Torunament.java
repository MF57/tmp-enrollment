package org.tmp.enrollment.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class Torunament {

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
}
