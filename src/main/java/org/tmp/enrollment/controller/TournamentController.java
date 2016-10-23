package org.tmp.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tmp.enrollment.domain.entities.Tournament;
import org.tmp.enrollment.service.TournamentService;

import java.util.List;

@RestController
@RequestMapping("/enrollment/tournaments")
public class TournamentController {

    private final TournamentService service;

    @Autowired
    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Tournament> getByIds(@RequestBody List<Long> ids) {
        return service.getAllByIds(ids);
    }
}
