package org.tmp.enrollment.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Capacity {

    private ParticipantType type;
    private int participantCount;
    private int participantArity;

    public enum ParticipantType {
        PLAYER, TEAM
    }
}
