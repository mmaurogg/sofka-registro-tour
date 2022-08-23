package co.com.sofka.registrytour.repository;

import co.com.sofka.registrytour.collections.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
    Flux<Team> findByCountry(String country);

    Mono<Team> findByCode(String code);
}
