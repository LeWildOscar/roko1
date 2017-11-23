package com.itesm.roko.endpoint;


import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.User;
import com.itesm.roko.service.TournamentService;
import com.itesm.roko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentEndpoint {

    @Autowired
    private TournamentService tournamentService;

    @POST
    @Path("/torneos")
    public Response insert(@RequestBody Tournament tournament){

        Optional<Tournament>tournamentDB = tournamentService.insert(tournament);

        Response response;
        if(tournamentDB.isPresent()){
            response = Response.ok(tournamentDB.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }
    @GET
    @Path("/torneos")
    public Response list(){
        Optional<List<Tournament>>tournaments = tournamentService.list();
        Response response;
        if(tournaments.isPresent()){
            response = Response.ok(tournaments.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }

    @GET
    @Path("/torneos/{public_identifier}")
    public Response search(@PathParam("public_identifier") String public_identifier){
        Optional<Tournament>tournament = tournamentService.getByPublicIdentifier(public_identifier);
        Response response;
        if(tournament.isPresent()){
            response = Response.ok(tournament.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }


}
