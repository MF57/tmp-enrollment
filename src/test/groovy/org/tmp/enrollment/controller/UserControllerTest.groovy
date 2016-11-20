package org.tmp.enrollment.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.tmp.enrollment.controller.error.Unauthorized
import org.tmp.enrollment.domain.entities.Capacity
import org.tmp.enrollment.domain.entities.Tournament
import org.tmp.enrollment.plumbing.config.security.User
import org.tmp.enrollment.plumbing.config.security.UserAuthentication
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("integration")
class UserControllerTest extends Specification {

    @Autowired
    TournamentController tournamentController

    def 'new tournament is added'() {

        def authentication = createAuthentication('john')

        given: 'initially empty list of tournaments'
            def initialList = tournamentController.getMyTournaments(authentication)


        when:
            tournamentController.createTournament(createTournament(), authentication)

        then:
            initialList.organizedTournaments == []
            initialList.participatingTournaments == []
            tournamentController.getMyTournaments(authentication).organizedTournaments.size() == 1
            tournamentController.getMyTournaments(authentication).participatingTournaments.size() == 0
    }

    def 'user can edit his tournaments'() {

        def authentication = createAuthentication('icek')

        given: 'some existing tournament'
            def initialTournament = tournamentController.createTournament(createTournament(), authentication)
            def tournamentId = initialTournament.id

        when:
            def modifiedTournament = tournamentController.getTournamentById(tournamentId)
            modifiedTournament.description = 'modified description'
            modifiedTournament.name = 'modified name'
            tournamentController.updateTournament(modifiedTournament, tournamentId, authentication)

        then:
            tournamentController.getTournamentById(tournamentId).name == 'modified name'
            tournamentController.getTournamentById(tournamentId).description == 'modified description'
    }

    def 'user cannot edit other users tournaments'(){

        def organizer = createAuthentication('organizer')
        def spectator = createAuthentication('spectator')

        given:
            def tournament = tournamentController.createTournament(createTournament(), organizer)
            def tournamentId = tournament.id

        when:
            tournamentController.updateTournament(tournament, tournamentId, spectator)

        then:
            thrown(Unauthorized)
    }

    def createTournament() {
        Tournament newTournament = new Tournament()
        newTournament.name = 'tournamentName'
        newTournament.capacity = new Capacity(Capacity.ParticipantType.PLAYER, 16, 1)
        newTournament.description = 'very good tournament, sir'
        newTournament
    }

    def createAuthentication(String name) {
        new UserAuthentication(new User(name, ["ROLE_USER"]))
    }
}
