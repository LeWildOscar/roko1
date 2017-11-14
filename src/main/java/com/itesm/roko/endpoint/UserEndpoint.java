package com.itesm.roko.endpoint;


import com.itesm.roko.domain.User;
import com.itesm.roko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Path("/v1")
//@RequestMapping("v1")
@Produces(MediaType.APPLICATION_JSON)

public class UserEndpoint {

    @Autowired
    private UserService userService;

    @GET
    @Path("/usuario/{username}")
    public Response search(@PathParam("username") String username){
        Optional<User>user = userService.getUser(username);

        Response response;
        if(user.isPresent()) {
            response = Response.ok(user.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;

    }

    @GET
    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @Path("/usuarios")
    public  Response list(){
        Optional<List<User>>users =  userService.list();
        Response response;
        if (users.isPresent()){
            response = Response.ok(users.get()).build();

        }else{
            response = Response.noContent().build();
        }

        return response;
    }

    @POST
    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public ResponseEntity<User> insert(@RequestBody User user){
        user.setTotal_points(0);
        user.setLosts(0);
        user.setMoney_in(0);
        user.setMoney_out(0);
        user.setPlayed(0);
        user.setTotal_money(0);
        Optional<User>userBD= userService.insert(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PUT
    @Path("/usuario/{uuid}")
    public Response update(@PathParam("uuid") String uuid, User user){
        user.setUuid(uuid);
        Optional<User> userDB = userService.update(user);
        Response response;
        if(userDB.isPresent()) {
            response = Response.ok(userDB.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }

    @DELETE
    @Path("/usuario/{uuid}")
    public Response delete(@PathParam("uuid") String uuid){
        Optional<User> user = userService.delete(uuid);
        Response response;
        if(user.isPresent()){
            response = Response.ok(user.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }
}
