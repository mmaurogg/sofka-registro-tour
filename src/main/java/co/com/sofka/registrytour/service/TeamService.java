package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.collections.Team;
import co.com.sofka.registrytour.repository.CyclistRepository;
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

    @Autowired
    CyclistRepository cyclistRepository;

    public Mono<Team> add(Team team) throws IllegalAccessException {
        if(team.getCode().length() == 3) {
            return teamRepository.save(team);
        }
        throw new IllegalAccessException("El código debe tener 3 caracteres");
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

    //TODO: validaciones
    public Mono<Team> addCyclist(String idTeam, Cyclist cyclist) throws IllegalStateException {


        return cyclistRepository.findById(cyclist.getId())
                .flatMap(i-> {
                    if (i.getIdEquipo() != null) {
                        throw new IllegalStateException("El ciclista ya tiene un equipo asignado");

                    }
                        i.setIdEquipo(idTeam);
                        cyclistRepository.save(i).subscribe();

                        return teamRepository.findById(idTeam).flatMap(team -> {
                            List<Cyclist> cyclists = team.getCyclists();
                            if (cyclists.size() >= 8) {
                                throw new IllegalStateException("Un equipo no puede tener mas de 8 competidores");
                            }
                            cyclists.add(i);
                            team.setCyclists(cyclists);
                            return teamRepository.save(team);
                        });
                });


    }

    //TODO: validaciones

    public Mono<Team> deleteCyclistToTeam(String idTeam, String idCyclist) {
        return teamRepository.findById(idTeam).flatMap(team -> {
            List<Cyclist> cyclists = team.getCyclists()
                            .stream().filter(i -> !i.getId().equals(idCyclist))
                            .collect(Collectors.toList());
            team.setCyclists(cyclists);

            cyclistRepository.findById(idCyclist).subscribe(i-> {
                i.setIdEquipo(null);
                cyclistRepository.save(i).subscribe();
            });

            return teamRepository.save(team);
        });
    }
}
