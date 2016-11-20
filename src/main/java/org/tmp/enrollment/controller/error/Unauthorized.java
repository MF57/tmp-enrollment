package org.tmp.enrollment.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class Unauthorized extends TournamentException {
    public Unauthorized(String message) {
        super(message);
    }
}
