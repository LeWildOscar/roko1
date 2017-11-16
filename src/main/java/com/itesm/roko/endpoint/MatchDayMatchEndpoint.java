package com.itesm.roko.endpoint;


import com.itesm.roko.domain.Jornada_match;
import com.itesm.roko.service.MatchDayMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)


public class MatchDayMatchEndpoint {


    @Autowired
    private MatchDayMatchService matchdayMatchService;


    @POST
    @Path("/partidoJornada")
    public Response insertarPartidoJornada (Jornada_match jornada_match) {

        return null;

    }








}
