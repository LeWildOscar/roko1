package com.itesm.roko.endpoint;



import com.itesm.roko.domain.User;
import com.itesm.roko.domain.User_friend;
import com.itesm.roko.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import java.util.List;

@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class UserFriendEndpoint {

    @Autowired
    private UserFriendService userFriendservice;

    /**
     * Obtiene la lista de amigos de un username
     * return: List<User>
     * */
    @GET
    @Path("/usuarios/{username}/amigos")
    public Response getFriends(@PathParam("username") String username) {
        Optional<List<User>> user_friend_db = userFriendservice.getFriendsList(username);
        Response response;
        if (user_friend_db.isPresent()) {
         response = Response.ok(user_friend_db.get()).build();
        } else {
         response = Response.noContent().build();

        }
        return response;
    }

    /**
    * Agrega a "username" un amigo pasado por requestbody.
    * return: user_friend
    * */
    @POST
    @Path("/usuarios/{username}/amigos")
    public Response agregarAmigo(@PathParam("username")String username, @RequestBody User user) {
        Optional<User_friend> user_friend_db = userFriendservice.insertUserFriend(user,username);
        Response response;
        if (user_friend_db.isPresent()) {
            response = Response.ok(user_friend_db.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }



    @DELETE
    @Path("/usuarios/{username}/amigos/{uuid}")
    public Response borrarAmigo (@PathParam("username")String username, @PathParam("uuid")String uuid) {
        Optional<User_friend> user_friend_db = userFriendservice.borrarAmigo(username,uuid);
        Response response;
        if (user_friend_db.isPresent()) {
            response = Response.ok(user_friend_db.get()).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }



}
