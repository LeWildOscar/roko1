package com.itesm.roko.endpoint;



import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.Tournament_user;
import com.itesm.roko.service.Tournament_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.List;

@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentUserEndpoint {

    @Autowired
    private Tournament_userService tournament_userService;



    /**
     *
     * Agrega a un torneo existente un usuario existente
     * return: un objeto tournament_user
     * */
    @POST
    @Path("/usuarios/{username}/torneo")
    public Response agregarTorneoUsuario(@RequestBody Tournament_user tournament_user, @PathParam("username") String username) {
        Optional<Tournament_user> tournament_user_response = tournament_userService.insert(tournament_user,username);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }



    /**
     * Devuelve los torneos en los que el usuario participa
     *
     * Agregar desde el frontend el id de torneo
     *
     * return: List<Tournament>
     * */

    @GET
    @Path("/usuarios/{username}/torneos")
    public Response getTorneosParticipaUsuario (@PathParam("username")String username) {
        Optional<List<Tournament>> tournament_user_response = tournament_userService.getTournamentsUser(username);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response.get()).build();
        } else {
            response = Response.noContent().build();
        }

        return response;
    }

    @GET
    @Path("/usuarios/{username}/torneos/admin")
    public Response getTorneosPropiosUsuario (@PathParam("username")String username) {
        Optional<List<Tournament>> tournament_user_response = tournament_userService.getTournamentsAdminUser(username);
        Response response;
        if (tournament_user_response.isPresent()) {
            response = Response.ok(tournament_user_response.get()).build();
        } else {
            response = Response.noContent().build();
        }

        return response;
    }




    @GET
    @Path("/usuarios/{username}/torneo/{uuid}")
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
    @Path("/usuarios/{username}/torneo/{uuid}")
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
    @Path("/usuarios/{username}/torneo/{uuid}")
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
