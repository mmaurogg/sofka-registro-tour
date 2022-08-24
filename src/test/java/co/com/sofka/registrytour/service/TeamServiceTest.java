package co.com.sofka.registrytour.service;

import co.com.sofka.registrytour.collections.Cyclist;
import co.com.sofka.registrytour.collections.Team;
import co.com.sofka.registrytour.repository.CyclistRepository;
import co.com.sofka.registrytour.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;

    @Mock
    TeamRepository teamRepository;

    @Mock
    CyclistRepository cyclistRepository;

    @Test
    public void addCyclistTest() {

    }



}
