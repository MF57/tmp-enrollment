package org.tmp.enrollment.domain.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmp.enrollment.domain.entities.*;
import org.tmp.enrollment.service.TeamService;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TournamentFilters {

    private final TeamService teamService;

    @Autowired
    public TournamentFilters(TeamService teamService) {
        this.teamService = teamService;
    }

    public Predicate<Tournament> enrollableFor(String userName) {
        return open()
                .and(not(full()))
                .and(not(isOrganizer(userName)))
                .and(not(isReferee(userName)));
    }

    public Predicate<Tournament> open() {
        return tournament -> tournament.getState() == TournamentState.ENROLLMENT_OPEN;
    }

    // Don't tell anyone I wrote this
    public Predicate<Tournament> full() {
        return tournament -> {
            Enrollment enrollment = tournament.getEnrollment();
            Capacity capacity = tournament.getCapacity();

            if(capacity.getType() == Capacity.ParticipantType.PLAYER) {
              return enrollment.getEnrolledParticipantIds().size()
                      == capacity.getParticipantCount();
          } else {
              List<Team> enrolledTeams = teamService.getByIds(enrollment.getEnrolledParticipantIds());
              if(enrolledTeams.size() < capacity.getParticipantCount()) {
                  return true;
              } else {
                  return enrolledTeams.stream()
                          .map(Team::getUserNames)
                          .map(List::size)
                          .filter(teamSize -> teamSize < capacity.getParticipantArity())
                          .count() > 0;
              }
          }
        };
    }

    public Predicate<Tournament> isOrganizer(String userName) {
        return tournament -> tournament.getOrganizerName().equals(userName);
    }

    public Predicate<Tournament> isReferee(String userName) {
        return tournament -> tournament.getReferees().contains(userName);
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
}
