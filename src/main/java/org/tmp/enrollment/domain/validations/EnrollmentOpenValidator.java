package org.tmp.enrollment.domain.validations;

import org.springframework.stereotype.Component;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.TournamentState;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;
import org.tmp.enrollment.util.StreamUtil;

import java.util.Objects;

@Component
public class EnrollmentOpenValidator implements TournamentChangeValidation {

    @Override
    public void validate(Tournament original, Tournament modified) throws TournamentModificationError {
        if(isOpeningChange(original, modified) && !canBeOpened(modified)) {
            throw new TournamentModificationError("Cannot open tournament, because it's missing some details");
        }
    }

    private boolean canBeOpened(Tournament modified) {

        return StreamUtil.count(Objects::isNull,
                modified.getBackupEndTime(),
                modified.getBackupStartTime(),
                modified.getStartTime(),
                modified.getName(),
                modified.getDescription(),
                modified.getOrganizerName(),
                modified.getStrategy(),
                modified.getPlace(),
                modified.getSport(),
                modified.getCapacity()) == 0;
    }

    private boolean isOpeningChange(Tournament original, Tournament modified) {
        return notYetOpened(original)
                && modified.getState() == TournamentState.ENROLLMENT_OPEN;
    }

    private boolean notYetOpened(Tournament original) {
        return original.getState() == TournamentState.CREATED;
    }
}
