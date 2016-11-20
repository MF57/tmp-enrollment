package org.tmp.enrollment.domain.validations.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmp.enrollment.controller.error.TournamentException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TournamentModificationError extends TournamentException {
    public TournamentModificationError(String message) {
        super(message);
    }
}
