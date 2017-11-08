package com.itesm.roko.endpoint;


import com.itesm.roko.domain.Tournament;
import com.itesm.roko.domain.User;
import com.itesm.roko.service.TournamentService;
import com.itesm.roko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("v1")
@Produces(MediaType.APPLICATION_JSON)
public class TournamentEndpoint {

    @Autowired
    private TournamentService tournamentService;

    //@POST
    @RequestMapping(value = "/tournament", method = RequestMethod.POST)
    public ResponseEntity<Tournament> insert(@RequestBody Tournament tournament){

        Optional<Tournament>tournamentDB = tournamentService.insert(tournament);

        return new ResponseEntity<Tournament>(tournamentDB.get(), HttpStatus.OK);
    }


}
