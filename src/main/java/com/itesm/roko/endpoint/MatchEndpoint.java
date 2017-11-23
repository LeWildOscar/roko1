package com.itesm.roko.endpoint;

import com.itesm.roko.domain.Match;
import com.itesm.roko.service.MatchService;
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
@Produces(MediaType.APPLICATION_JSON)
public class MatchEndpoint {

    @Autowired
    private MatchService matchService;

    @GET
    @Path("/partidos")
    public  Response list(){
        Optional<List<Match>>users =  matchService.list();
        Response response;
        if (users.isPresent()){
            response = Response.ok(users.get()).build();

        }else{
            response = Response.noContent().build();
        }

        return response;
    }

}
