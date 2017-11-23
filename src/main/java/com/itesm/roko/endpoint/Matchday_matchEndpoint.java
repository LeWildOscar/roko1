package com.itesm.roko.endpoint;


import com.itesm.roko.domain.Matchday_match;
import com.itesm.roko.service.Matchday_matchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Component
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)


public class Matchday_matchEndpoint {


    @Autowired
    private Matchday_matchService matchday_matchService;


    @POST
    @Path("/partido-jornadas")
    public Response insert (Matchday_match matchday_match) {

        Optional<Matchday_match>matchday_matchDB = matchday_matchService.insert(matchday_match);
        Response response;
        if(matchday_matchDB.isPresent()) {
            response = Response.ok(matchday_matchDB.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;

    }










}
