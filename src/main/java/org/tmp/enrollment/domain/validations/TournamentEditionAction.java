package org.tmp.enrollment.domain.validations;

import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;

public interface TournamentEditionAction {
    void performAction(Tournament original, Tournament modified) throws TournamentModificationError;
}
