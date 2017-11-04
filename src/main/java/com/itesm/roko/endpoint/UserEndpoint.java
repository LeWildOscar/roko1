package com.itesm.roko.endpoint;


import com.itesm.roko.domain.User;
import com.itesm.roko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Path("/v1")
public class UserEndpoint {

    @Autowired
    private UserService userService;

    @GET
    @Path("/user")
    public Response search(){
        Optional<List<User>>users =  userService.list();
        Response response;
        if ( users.isPresent()){
            response = Response.ok(users.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }
}
