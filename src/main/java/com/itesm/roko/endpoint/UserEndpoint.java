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
@RestController
@RequestMapping("v1")
@Produces(MediaType.APPLICATION_JSON)

public class UserEndpoint {

    @Autowired
    private UserService userService;

    @GET
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public  Response search(){
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
    @RequestMapping(value = "/user", method = RequestMethod.POST)
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
}
