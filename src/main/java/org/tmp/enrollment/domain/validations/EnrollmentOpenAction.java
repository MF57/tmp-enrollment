package org.tmp.enrollment.domain.validations;

import org.springframework.stereotype.Component;
import org.tmp.enrollment.domain.entities.Enrollment;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.TournamentState;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;
import org.tmp.enrollment.util.StreamUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class EnrollmentOpenAction implements TournamentEditionAction {

    @Override
    public void performAction(Tournament original, Tournament modified) throws TournamentModificationError {
        boolean enrollmentOpened = isEnrollmentOpening(original, modified);
        if(enrollmentOpened && !canBeOpened(modified)) {
            throw new TournamentModificationError("Cannot open tournament, because it's missing some details");
        }
        if (enrollmentOpened) {
            modified.setEnrollment(new Enrollment(LocalDateTime.now().plusWeeks(1), new ArrayList<>()));
            modified.setReferees(new ArrayList<>());
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

    private boolean isEnrollmentOpening(Tournament original, Tournament modified) {
        return notYetOpened(original)
                && modified.getState() == TournamentState.ENROLLMENT_OPEN;
    }

    private boolean notYetOpened(Tournament original) {
        return original.getState() == TournamentState.CREATED;
    }
}
