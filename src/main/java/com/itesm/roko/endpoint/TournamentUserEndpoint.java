package com.itesm.roko.endpoint;



import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.service.Tournament_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.List;

@Component
@RestController
@RequestMapping("v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentUserEndpoint {

    @Autowired
    private Tournament_userService tournament_userService;



    //Agregar un torneo a un username
    @POST
    @Path("/usuario/{username}/torneo")
    public Response agregarTorneoUsuario(@RequestBody Tournament_user tournament_user, @PathParam("username") String username) {
        Optional<Tournament_user> tournamet_user_response = tournament_userService.insert(tournament_user,username);
        Response response;
        if (tournamet_user_response.isPresent()) {
            response = Response.ok(tournamet_user_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }




    @GET
    @Path("/usuario/{username}/torneos")
    public Response getUserTournaments () {
        Optional<List<Tournament_user>> tournament_user_response = tournament_userService.getUserTournaments();
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response.get()).build();
        } else {
            response = Response.noContent().build();
        }

        return response;
    }

    @GET
    @Path("/usuario/{username}/torneo/{uuid}")
    public Response getUserTournamentByUuid(@PathParam("username")String username,
                                            @PathParam("uuid") String uuid) {
        Optional<Tournament_user> tournament_user_response = tournament_userService.getTournamentUserByUuid(uuid);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    //Modifica un torneo de usuario concreto por uuid




    @PUT
    @Path("/usuario/{username}/torneo/{uuid}")
    public Response modificarTorneoUsuario(@RequestBody Tournament_user tournament_user,
    @PathParam("username")String username, @PathParam("uuid") String uuid) {
        Optional<Tournament_user> tournament_user_response = tournament_userService.update(username,tournament_user,uuid);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @DELETE
    @Path("/usuario/{username}/torneo/{uuid}")
    public Response deleteUserTournamentByUuid(@PathParam("username")String username, @PathParam("uuid") String uuid) {

        Optional<Tournament_user> tournament_userService_response = tournament_userService.delete(uuid);
        Response response;

        if (tournament_userService_response.isPresent()) {
            response = Response.ok(tournament_userService_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }










}
