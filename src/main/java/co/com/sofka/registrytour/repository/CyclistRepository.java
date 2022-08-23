package co.com.sofka.registrytour.repository;

import co.com.sofka.registrytour.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {

    Flux<Cyclist> findByCountry(String country);

    Mono<Cyclist> findByNumber(String number);

}
