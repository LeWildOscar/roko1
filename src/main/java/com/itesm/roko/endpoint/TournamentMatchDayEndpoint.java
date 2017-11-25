package com.itesm.roko.endpoint;


import com.itesm.roko.domain.Match;
import com.itesm.roko.domain.Matchday_match;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_matchday;
import org.springframework.http.HttpStatus;
import com.itesm.roko.service.TournamentMatchDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentMatchDayEndpoint {

    @Autowired
    private TournamentMatchDayService tournamentMatchDayService;


    /*
    @GET
    @Path("/jornadas/{uuidJornada}")
    public Response obtenerTorneoJornada (@PathVariable("uuidJornada")String uuidJornada ) {
        Optional<Tournament_matchday> aux_tournament_matchday = tournamentMatchDayService.getTournamentMatchDayByUuid(uuidJornada);
        Response response;
        if (aux_tournament_matchday.isPresent()) {
            response = Response.ok(aux_tournament_matchday.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }*/

    @GET
    @Path("/jornadas")
    public Response obtenerTorneosJornada () {
        Optional<List<Tournament_matchday>> list_tournament_matchday = tournamentMatchDayService.getTournaments();
        Response response;
        if (list_tournament_matchday.isPresent()) {
            response = Response.ok(list_tournament_matchday.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @POST
    @Path("/jornadas")
    public Response insert(Tournament_matchday tournament_matchday){
        Optional<Tournament_matchday>tournament_matchdayDB = tournamentMatchDayService.insert(tournament_matchday);
        Response response;
        if (tournament_matchdayDB.isPresent()){
            response = Response.ok(tournament_matchdayDB.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;

    }

    @GET
    @Path("/jornadas/{tournament_id}")
    public Response getByTournament(@PathParam("tournament_id")String tournament_id){
        Optional<List<Tournament_matchday>> tournament_matchday = tournamentMatchDayService.tournamentMatchdays(tournament_id);
        Response response;
        if (tournament_matchday.isPresent()){
            response = Response.ok(tournament_matchday.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }
    /*

     */

    @GET
    @Path("/jornadas/{tournament_matchday_id}/partidos")
    public Response getTournamentMatches(@PathParam("tournament_matchday_id")String tournament_matchday_id){
        Optional<List<Match>> tournament_matchday = tournamentMatchDayService.matchdayMatches(tournament_matchday_id);
        Response response;
        if (tournament_matchday.isPresent()){
            response = Response.ok(tournament_matchday.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }

    @POST
    @Path("/jornadas/partidos")
    public Response insertMatchOnMatchDay(@RequestBody Matchday_match matchday_match){
        Optional<Matchday_match> matchday_matchDB = tournamentMatchDayService.insertMatch(matchday_match);
        Response response;
        if (matchday_matchDB.isPresent()){
            response = Response.ok(matchday_matchDB.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }












}
