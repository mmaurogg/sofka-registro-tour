package co.com.sofka.registrytour.controller;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.collections.Team;
import co.com.sofka.registrytour.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cyclist")
public class CylistController {
    @Autowired
    CyclistService cyclistService;

    @PostMapping()
    public ResponseEntity<Mono<Cyclist>> addCyclist(@RequestBody Cyclist cyclist){
        try {
            return new ResponseEntity(cyclistService.save(cyclist), HttpStatus.CREATED );
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //public Mono<ServerResponse> addCyclist(ServerRequest serverRequest){
    //    return serverRequest.bodyToMono(Cyclist.class)
    //            .flatMap(cyclist -> cyclistService.add(cyclist))
    //          .flatMap(cyclistSaved -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
    //                   .body(Mono.just(cyclistSaved), Cyclist.class));
    //}

    @GetMapping()
    public ResponseEntity<Flux<Cyclist>> getAllCyclists(){
        return new ResponseEntity(cyclistService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idCyclist}")
    public ResponseEntity<Mono<Cyclist>> getCyclistById(@PathVariable("idCyclist") String id){
        return new ResponseEntity(cyclistService.getById(id),HttpStatus.OK);
    }

    @DeleteMapping(value="/{idCyclist}")
    public ResponseEntity<Mono<Void>> deleteCyclistById(@PathVariable("idCyclist") String id){
        return new ResponseEntity(cyclistService.deleteById(id), HttpStatus.OK);
    }
    //TODO: borrar
    /*
    public ResponseEntity<Map<String,Object>> deleteCyclistById(@PathVariable("idCyclist") String id){
        Map<String, Object> response = new HashMap<>();
        try{
            cyclistService.deleteById(id);
            response.put("message", "Se eliminó el ciclista con id ".concat(id));
        }catch (DataAccessException e){
            response.put("message","Ocurrió un error al eliminar el ciclista con id ".concat(id) );
            response.put("error", e.getMostSpecificCause().getMessage() );
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
     */

    @PutMapping("/{idCyclist}")
    public ResponseEntity<Mono<Cyclist>> updateCyclists(@PathVariable("idCyclist") String idCyclist, @RequestBody Cyclist newCyclist) throws IllegalAccessException {


        Mono<Cyclist> cyclist = cyclistService.getById(idCyclist)
                .flatMap(i -> {

                    i.setName(newCyclist.getName());
                    i.setNumber(newCyclist.getNumber());
                    i.setNationality(newCyclist.getNationality());
                    i.setIdEquipo(newCyclist.getIdEquipo());

                    try {
                        return cyclistService.save(i);
                    } catch (IllegalAccessException e) {

                        //throw new RuntimeException(e);
                        return Mono.error(new RuntimeException(e));
                    }
                });

        if (cyclist.map(i -> i.getId()) != null) {
            return new ResponseEntity(cyclist, HttpStatus.CREATED);
        }
        return new ResponseEntity(cyclist, HttpStatus.NOT_FOUND);


        /*

        Cyclist cyclist = cyclistService.getById(idCyclist).toFuture().join();

       //if (!cyclist.getId().equals(null)) {
        if (cyclist.getId().equals(null)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        newCyclist.setId(idCyclist);
        return new ResponseEntity(cyclistService.save(newCyclist), HttpStatus.CREATED);


         */

    }


    //TODO: validaciones
    @GetMapping(value="/nationality/{nationality}")
    public ResponseEntity<Flux<Cyclist>> getCyclistsBynationality(@PathVariable("nationality") String nationality){
        try {
            return new ResponseEntity(cyclistService.getBynationality(nationality),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //TODO: validaciones
    @GetMapping(value="/number/{number}")
    public ResponseEntity<Mono<Cyclist>> getCyclistsByNumber(@PathVariable("number") String number){
        try {
            return new ResponseEntity(cyclistService.getByNumber(number), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
