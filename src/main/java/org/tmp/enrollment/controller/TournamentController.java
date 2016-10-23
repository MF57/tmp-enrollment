package org.tmp.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.tmp.enrollment.controller.dtos.UserTournaments;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.domain.entities.User;
import org.tmp.enrollment.service.TournamentService;
import org.tmp.enrollment.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;
    private final UserService userService;

    @Autowired
    public TournamentController(TournamentService tournamentService, UserService userService) {
        this.tournamentService = tournamentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable("id") String id) {
        return tournamentService.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Tournament> getByIds(@RequestBody List<String> ids) {
        return tournamentService.getAllByIds(ids);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public UserTournaments getMyTournaments(Authentication authentication) {
        String myUserName = getUserName(authentication);
        return userService.getTournamentsForUser(myUserName);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Tournament createTournament(@RequestBody Tournament tournament, Authentication authentication) {
        String userName = getUserName(authentication);
        tournament.setOrganizerName(userName);
        tournament.setId(null);
        return tournamentService.save(tournament);
    }

    private String getUserName(Authentication authentication) {
        return ((org.tmp.enrollment.plumbing.config.security.User) authentication.getPrincipal()).getUsername();
    }
}
