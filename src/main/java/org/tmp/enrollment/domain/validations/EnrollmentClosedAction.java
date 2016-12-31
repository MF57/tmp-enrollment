package org.tmp.enrollment.domain.validations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.TournamentState;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;

/**
 * Created by mf57 on 31.12.2016.
 */
@Component
@ConfigurationProperties(prefix="urls")
@Qualifier("closer")
public class EnrollmentClosedAction implements TournamentEditionAction {

    private final  RestTemplate template = new RestTemplate();
    private String coreUrl;


    @Override
    public void performAction(Tournament original, Tournament modified) throws TournamentModificationError {
        boolean enrollmentClosed = isEnrollmentClosing(original, modified);
        if(enrollmentClosed && !canBeClosed(modified)) {
            throw new TournamentModificationError("Cannot close enrollment, because no one has enrolled");
        }
        if (enrollmentClosed) {
            ResponseEntity<Boolean> response = template.postForEntity(coreUrl + "/tournaments", modified, Boolean.class);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                throw new TournamentModificationError("Could not create tournament tree");
            }
        }

    }

    private boolean canBeClosed(Tournament modified) {
        return modified.getEnrollment().getEnrolledParticipantIds().size() > 0;
    }

    private boolean isEnrollmentClosing(Tournament original, Tournament modified) {
        return original.getState() == TournamentState.ENROLLMENT_OPEN
                && modified.getState() == TournamentState.ENROLLMENT_COMPLETE;
    }

    public String getCoreUrl() {
        return coreUrl;
    }

    public void setCoreUrl(String coreUrl) {
        this.coreUrl = coreUrl;
    }
}
