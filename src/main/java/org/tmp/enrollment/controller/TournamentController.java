package org.tmp.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.tmp.enrollment.controller.dtos.UserTournaments;
import org.tmp.enrollment.controller.error.TournamentDoesNotExist;
import org.tmp.enrollment.controller.error.TournamentException;
import org.tmp.enrollment.controller.error.Unauthorized;
import org.tmp.enrollment.domain.entities.*;
import org.tmp.enrollment.domain.validations.error.TournamentModificationError;
import org.tmp.enrollment.service.TournamentService;
import org.tmp.enrollment.service.UserService;

import java.time.LocalDateTime;
import java.util.Collections;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<Tournament> getByIds(@RequestBody List<String> ids) {
        return tournamentService.getAllByIds(ids);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public UserTournaments getMyTournaments(Authentication authentication) {
        String myUserName = getUserName(authentication);
        return userService.getTournamentsForUser(myUserName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tournament createTournament(@RequestBody Tournament tournament, Authentication authentication) {
        String userName = getUserName(authentication);
        tournament.setOrganizerName(userName);
        tournament.setState(TournamentState.CREATED);
        tournament.setId(null);
        return tournamentService.save(tournament);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Tournament updateTournament(@RequestBody Tournament tournament, @PathVariable String id, Authentication authentication) throws TournamentException {
        Tournament byId = tournamentService.getById(id);
        if(byId == null) {
            throw new TournamentDoesNotExist();
        } else if(!canModify(getUserName(authentication), byId)) {
            throw new Unauthorized("You cannot modify tournament you are not organizer of!");
        } else {
            return tournamentService.update(tournament);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTournament(@PathVariable String id, Authentication authentication) throws TournamentException {
        Tournament tournament = tournamentService.getById(id);
        if(tournament == null) {
            throw new TournamentDoesNotExist();
        } else if(!canModify(getUserName(authentication), tournament)) {
            throw new Unauthorized("You cannot modify tournament you are not organizer of!");
        } else {
            tournamentService.delete(id);
        }
    }




    @RequestMapping(value = "/enrollable", method = RequestMethod.GET)
    public List<Tournament> getAllEnrollableForMe(Authentication authentication) {
        return tournamentService.getEnrollableFor(getUserName(authentication));
    }

    private boolean canModify(String userName, Tournament byId) {
        return byId.getOrganizerName().equals(userName);
    }

    private String getUserName(Authentication authentication) {
        return ((org.tmp.enrollment.plumbing.config.security.User) authentication.getPrincipal()).getUsername();
    }
}
