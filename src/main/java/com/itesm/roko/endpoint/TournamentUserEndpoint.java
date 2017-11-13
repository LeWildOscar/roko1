package com.itesm.roko.endpoint;



import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.service.Tournament_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.Produces;
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
    @RequestMapping(value = "/usuario/{username}/torneo", method = RequestMethod.POST)
    public Response insert(@RequestBody Tournament_user tournament_user, @PathVariable("username")String username) {
        Optional<Tournament_user> tournamet_user_response = tournament_userService.insert(tournament_user,username);
        Response response;
        if (tournamet_user_response.isPresent()) {
            response = Response.ok(tournamet_user_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }




    //Devolver todos los torneos de un username
    @RequestMapping(value = "/usuario/{username}/torneo", method = RequestMethod.GET)
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

    //Devuelve el torneo concreto por uuid
    @RequestMapping(value = "/usuario/{username}/torneo/{uuid}",method = RequestMethod.GET)
    public Response getUserTournamentByUuid(@PathVariable("uuid") String uuid) {
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
    public Response modificarTorneoUsuario(@RequestBody Tournament_user tournament_user, @PathParam("uuid") String uuid) {
        Optional<Tournament_user> tournament_user_response = tournament_userService.update(tournament_user,uuid);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @RequestMapping(value = "/usuario/{username}/torneo/{uuid}",method = RequestMethod.DELETE)
    public Response deleteUserTournamentByUuid(@PathVariable("uuid")String uuid) {

        Optional<Boolean> tournament_userService_response = tournament_userService.delete(uuid);
        Response response;

        if (tournament_userService_response.isPresent()) {
            response = Response.ok(tournament_userService_response).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }










}
