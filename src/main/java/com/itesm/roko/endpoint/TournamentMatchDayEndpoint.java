package com.itesm.roko.endpoint;


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
import java.util.Optional;


@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentMatchDayEndpoint {

    @Autowired
    private TournamentMatchDayService tournamentMatchDayService;


    @POST
    @Path("/torneo/{uuid}/jornada")
    public Response agregarTorneoJornada (@RequestBody Tournament_matchday tournament_matchday, @PathVariable("uuid") String uuid) {
        Optional<Tournament_matchday> aux_tournament_matchday = tournamentMatchDayService.insert(tournament_matchday,
                uuid);
        Response response;
        if (aux_tournament_matchday.isPresent()) {
            response = Response.ok(aux_tournament_matchday.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @GET
    @Path("/jornada/{uuidJornada}")
    public Response obtenerTorneoJornada (@PathVariable("uuidJornada")String uuidJornada ) {
        Optional<Tournament_matchday> aux_tournament_matchday = tournamentMatchDayService.getTournamentMatchDayByUuid(uuidJornada);
        Response response;
        if (aux_tournament_matchday.isPresent()) {
            response = Response.ok(aux_tournament_matchday.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @GET
    @Path("/jornadas")
    public Response obtenerTorneosJornada ()






}
