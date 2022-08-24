package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.repository.CyclistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CyclistServiceTest {

    @InjectMocks
    CyclistService cyclistService;

    @Mock
    CyclistRepository repository;

    @Test
    public void getBynationalityTest(){

        Cyclist cyclist1 = new Cyclist("id1", "Jhon Ruedas", "001", "Colombiana","");
        Cyclist cyclist2 = new Cyclist("id2", "esteban llantas", "002", "Colombiana","");
        List<Cyclist> cyclists = new ArrayList<>();
        cyclists.add(cyclist1);
        cyclists.add(cyclist2);
        Flux<Cyclist> cyclistsFlux = Flux.fromIterable(cyclists);

        when(repository.findByNationality(Mockito.any(String.class))).thenReturn(cyclistsFlux);

        StepVerifier.create(cyclistService.getBynationality("Colombiana"))
                .expectNext(cyclist1)
                .expectNext(cyclist2)
                .expectComplete()
                .verify();
    }

    @Test
    public void getByNumberTest(){
        Cyclist cyclist1 = new Cyclist("id1", "Jhon Ruedas", "001", "Colombiana","");

        Mono<Cyclist> cyclistsMono = Mono.just(cyclist1);

        when(repository.findByNumber(Mockito.any(String.class))).thenReturn(cyclistsMono);

        StepVerifier.create(cyclistService.getByNumber("001"))
                .expectNext(cyclist1)
                .expectComplete()
                .verify();

    }

}
