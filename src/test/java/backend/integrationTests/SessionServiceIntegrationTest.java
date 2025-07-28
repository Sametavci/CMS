package backend.integrationTests;

import backend.application.services.SessionService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainMovie;
import backend.domain.models.DomainSession;
import backend.domain.ports.repositorys.IHallRepository;
import backend.domain.ports.repositorys.IMovieRepository;
import backend.domain.ports.repositorys.ISessionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.AfterEach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class SessionServiceIntegrationTest {
    DomainHall hall;
    List<Long> movieIds = new ArrayList<>();
    List<DomainSession> sessions = new ArrayList<>();
    @Autowired
    IHallRepository iHallRepository;
    @Autowired
    IMovieRepository iMovieRepository;
    @Autowired
    ISessionRepository iSessionRepository;
    @Autowired
    SessionService sessionService;


    @BeforeEach
    public void setUp() {
        hall = new DomainHall();
        hall.setName("IMAX Hall");
        hall.setCapacity(100);
        hall.setType("IMAX");
        hall = iHallRepository.save(hall);

        for (long i = 1; i <= 10; i++) {
            DomainMovie movie = new DomainMovie();
            movie.setDuration(150);
            movie.setGenre("Sci-Fic");
            movie.setPrice(i*10.00);
            movie.setTitle("TEST MOVIE"+i);
            DomainMovie savedMovie = iMovieRepository.save(movie);
            movieIds.add(savedMovie.getId());
        }


        for (int i = 1; i <= 100; i++) {
            DomainSession session = new DomainSession();
            session.setStartTime(LocalDateTime.now().plusDays(i % 30));
            session.setMovie(movieIds.get(i % 10));
            session.setHall(hall.getId());
            session.setPrice(i <= 20 ? 20.0 : 10.0);
            DomainSession savedSession = iSessionRepository.save(session);
            sessions.add(savedSession);
        }
    }

    @AfterEach
    public void tearDown() {
        for (DomainSession session : iSessionRepository.findAll()) {
            iSessionRepository.deleteById(session.getId());
        }

        for (DomainMovie movie : iMovieRepository.findAll()) {
            iMovieRepository.deleteById(movie.getId());
        }

        for (DomainHall hall : iHallRepository.findAll()) {
            iHallRepository.deleteById(hall.getId());
        }

        sessions.clear();
        movieIds.clear();
    }

    @Test
    public void TestGetAllSessionsFromHall(){
        List<Long> sessionIds = sessionService.getAllSessionsFromHall(hall.getId())
                .stream()
                .map(DomainSession::getId)
                .toList();

        List<Long> expectedIds = sessions.stream()
                .filter(m->m.getHall() == hall.getId())
                .map(DomainSession::getId)
                .toList();

        assertTrue(sessionIds.size() == expectedIds.size() && sessionIds.containsAll(expectedIds));
    }
    @Test
    public void TestGetAllSessionsFromMovie(){
        List<Long> sessionList = sessions.stream()
                .filter(m -> Objects.equals(m.getMovie(), 3L))
                .map(DomainSession::getId)
                .toList();
        List<Long> actualSessionIds = sessionService.getAllSessionsFromMovie(3L)
                .stream()
                .map(DomainSession::getId)
                .toList();
        assertEquals(sessionList.size(), actualSessionIds.size());
        assertTrue(sessionList.containsAll(actualSessionIds));
    }



}
