package org.tmp.enrollment.domain.entities;

public enum TournamentState {

    CREATED("Tournament has been created, but some information is missing"),
    DEFINED("Everything set up, enrollment awaits opening"),
    ENROLLMENT_OPEN("Enrollment for this tournament is on"),
    ENROLLMENT_COMPLETE("Enrollment for this tournament is complete");

    private final String description;

    TournamentState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.name(), description);
    }
}
