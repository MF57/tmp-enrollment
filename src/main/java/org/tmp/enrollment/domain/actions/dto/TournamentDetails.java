package org.tmp.enrollment.domain.actions.dto;

import lombok.Builder;

@Builder
public class TournamentDetails {
    private final String organizerUserName;
    private final String tournamentName;
    private final String cityName;


}
