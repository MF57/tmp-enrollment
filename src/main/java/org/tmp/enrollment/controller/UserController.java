package org.tmp.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tmp.enrollment.controller.error.UnknownUserException;
import org.tmp.enrollment.domain.entities.User;
import org.tmp.enrollment.service.UserService;

@RestController("/enrollment/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public User getCurrentUser() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUser(currentUserName).orElseThrow(
                () -> new UnknownUserException(currentUserName)
        );
    }
}
