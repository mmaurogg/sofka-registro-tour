package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.collections.Team;
import co.com.sofka.registrytour.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Mono<Team> add(Team team){
        return teamRepository.save(team);
    }

    public Flux<Team> getAll(){
        return teamRepository.findAll();
    }

    public Mono<Team> getById(String id){
        return teamRepository.findById(id);
    }

    public Mono<Void> delete(String id){
        return teamRepository.deleteById(id);
    }

    public Mono<Team> update(Team team){
        return teamRepository.save(team);
    }

    public Flux<Team> getByCountry(String country){
        return teamRepository.findByCountry(country);
    }

    //TODO: solucionar cuando hay mas de un equipo con un código (en la interface cambiar flux por mono y validaciones para código único)
    public Flux<Cyclist> getCyclistsByCode(String code){
        return teamRepository.findByCode(code)
                .flatMapIterable(team -> team.getCyclists());
    }

    //TODO: addCyclistToTeam
    //TODO: deleteCyclistToTeam

}
