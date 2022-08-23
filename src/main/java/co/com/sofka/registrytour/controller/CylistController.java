package co.com.sofka.registrytour.controller;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.service.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cyclist")
public class CylistController {
    @Autowired
    CyclistService cyclistService;

    @PostMapping("/add")
    public ResponseEntity<Mono<Cyclist>> addCyclist(@RequestBody Cyclist cyclist){
        return new ResponseEntity(cyclistService.add(cyclist), HttpStatus.CREATED );
    }

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
        return new ResponseEntity(cyclistService.delete(id),HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Mono<Cyclist>> updateCyclists(@RequestBody Cyclist cyclist){
        if (!cyclist.getId().equals(null)) {
            return new ResponseEntity(cyclistService.update(cyclist), HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //TODO: validaciones
    @GetMapping(value="/country/{country}")
    public ResponseEntity<Flux<Cyclist>> getCyclistsByCountry(@PathVariable("country") String country){
        return new ResponseEntity(cyclistService.getByCountry(country),HttpStatus.OK);
    }

    //TODO: validaciones
    @GetMapping(value="/number/{number}")
    public ResponseEntity<Mono<Cyclist>> getCyclistsByNumber(String number){
        return new ResponseEntity(cyclistService.getByNumber(number), HttpStatus.OK);
    }
}
