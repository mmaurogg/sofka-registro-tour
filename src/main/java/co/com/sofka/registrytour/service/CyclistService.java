package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.repository.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CyclistService {

    @Autowired
    CyclistRepository cyclistRepository;

    public Mono<Cyclist> add(Cyclist cyclist){
        return cyclistRepository.save(cyclist);
    }

    public Flux<Cyclist> getAll(){
        return cyclistRepository.findAll();
    }

    public Mono<Cyclist> getById(String id){
        return cyclistRepository.findById(id);
    }

    public Mono<Void> delete(String id){
        return cyclistRepository.deleteById(id);
    }

    public Mono<Cyclist> update(Cyclist cyclist){
        return cyclistRepository.save(cyclist);
    }

    public Flux<Cyclist> getByCountry(String country){
        return cyclistRepository.findByCountry(country);
    }

    public Mono<Cyclist> getByNumber(String number){
        return cyclistRepository.findByNumber(number);
    }
}
