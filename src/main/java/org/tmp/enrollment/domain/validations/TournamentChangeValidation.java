package org.tmp.enrollment.domain.validations;

import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;

public interface TournamentChangeValidation {
    void validate(Tournament original, Tournament modified) throws TournamentModificationError;
}
