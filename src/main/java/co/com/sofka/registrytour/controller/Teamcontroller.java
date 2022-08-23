package co.com.sofka.registrytour.controller;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.collections.Team;
import co.com.sofka.registrytour.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/team")
public class Teamcontroller {
    @Autowired
    TeamService teamService;

    @PostMapping()
    public ResponseEntity<Mono<Team>> addTeam(@RequestBody Team team){
        return new ResponseEntity(teamService.add(team), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Flux<Team>> getAllTeams(){
        return new ResponseEntity(teamService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<Mono<Team>> getTeamById(@PathVariable("idTeam") String id){
        return new ResponseEntity(teamService.getById(id),HttpStatus.OK);
    }

    @DeleteMapping(value="/{idTeam}")
    public ResponseEntity<Mono<Void>> deleteTeamById(@PathVariable("idTeam") String id){
        return new ResponseEntity(teamService.delete(id),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Mono<Team>> updateTeam(@RequestBody Team team){
        //if (!team.getId().equals(null)) {
        if (team.getId() != null) {
            return new ResponseEntity(teamService.update(team), HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //TODO: validaciones
    @GetMapping(value="/country/{country}")
    public ResponseEntity<Flux<Team>> getTeamsByCountry(@PathVariable("country") String country){
        return new ResponseEntity(teamService.getByCountry(country),HttpStatus.OK);
    }

    //TODO: validaciones
    @GetMapping(value="/code/{code}")
    public ResponseEntity<Flux<Cyclist>> getCyclistsByCode(@PathVariable("code") String code){
        return new ResponseEntity(teamService.getCyclistsByCode(code), HttpStatus.OK);
    }

    //TODO: addCyclistToTeam
    //TODO: deleteCyclistToTeam
}
