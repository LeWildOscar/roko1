package com.itesm.roko.endpoint;


import com.itesm.roko.domain.User;
import com.itesm.roko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("v1")
public class UserEndpoint {

    @Autowired
    private UserService userService;

    @GET
    @RequestMapping("/user")
    public Response search(){
        Optional<List<User>>users =  userService.list();
        System.out.println(users.isPresent());
        List<User>users1 = users.get();
        System.out.println(users1.size());
        System.out.println("username = "+users1.get(0).getUsername());
        Response response;
        if (users.isPresent()){
            response = Response.ok(users.get()).build();
        }else{
            response = Response.noContent().build();
        }
        return response;
    }
}
