package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.repository.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CyclistService {

    @Autowired
    CyclistRepository cyclistRepository;

    public Mono<Cyclist> save(Cyclist cyclist) throws IllegalAccessException {
        if(cyclist.getNumber().length() == 3){
            return cyclistRepository.save(cyclist);
        }
        throw new IllegalAccessException("No se guardó el registro, el número debe tener 3 dígitos");
    }

    public Flux<Cyclist> getAll(){
        return cyclistRepository.findAll();
    }

    public Mono<Cyclist> getById(String id){
        return cyclistRepository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
            return cyclistRepository.deleteById(id);
    }

    public Flux<Cyclist> getBynationality(String nationality){
        return cyclistRepository.findByNationality(nationality);
    }

    public Mono<Cyclist> getByNumber(String number){
        return cyclistRepository.findByNumber(number);
    }
}
