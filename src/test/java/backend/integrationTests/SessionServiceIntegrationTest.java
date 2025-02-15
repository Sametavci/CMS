package backend.integrationTests;

import backend.application.services.SessionService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainMovie;
import backend.domain.models.DomainSession;
import backend.domain.ports.repositorys.IHallRepository;
import backend.domain.ports.repositorys.IMovieRepository;
import backend.domain.ports.repositorys.ISessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

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

    @Test
    public void TestGetAllSessionsFromHall(){
        List<DomainSession> sessionList = sessions.stream().filter(m -> m.getHall() == hall.getId()).toList();
        assertTrue(sessionList.size() == sessionService.getAllSessionsFromHall(hall.getId()).size()
        && sessionService.getAllSessionsFromHall(hall.getId()).containsAll(sessionList));
    }

    @Test
    public void TestGetAllSessionsFromMovie(){
        List<DomainSession> sessionList = sessions.stream().filter(m -> m.getMovie() == movieIds.get(2)).toList();
        List<DomainSession> sessions = sessionService.getAllSessionsFromMovie(movieIds.get(2));
        assertTrue(sessionList.size() == sessions.size()
                && sessions.containsAll(sessionList));
    }
}
