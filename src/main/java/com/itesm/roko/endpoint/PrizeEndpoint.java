package com.itesm.roko.endpoint;

import com.itesm.roko.domain.Prize;
import com.itesm.roko.domain.Tournament;
import com.itesm.roko.service.PrizeService;
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
public class PrizeEndpoint {

    @Autowired
    private PrizeService prizeService;
    /*
    @POST
    @Path("/prize")
    public ResponseEntity<Prize> insert(@RequestBody Prize prize){


        Optional<Prize>prizeBD = prizeService.insert(prize);
    }*/


}
