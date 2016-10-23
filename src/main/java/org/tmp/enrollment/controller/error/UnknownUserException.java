package org.tmp.enrollment.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String userName) {
        super("User '" + userName + "' not found");
    }
}
