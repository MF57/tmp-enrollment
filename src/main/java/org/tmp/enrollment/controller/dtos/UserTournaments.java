package org.tmp.enrollment.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tmp.enrollment.domain.entities.Tournament;

import java.util.List;

@Data @AllArgsConstructor
public class UserTournaments {
    private String userName;
    private List<Tournament> organizedTournaments;
    private List<Tournament> participatingTournaments;
}
