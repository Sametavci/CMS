package backend.serviceTests;

import backend.application.services.SessionService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainMovie;
import backend.domain.models.DomainSession;
import backend.domain.ports.repositorys.ISessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionServiceTest {

    @Mock
    private ISessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    private DomainSession session;
    private DomainMovie movie;

    private DomainHall hall;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movie = new DomainMovie("Interstellar", "Sci-Fi", 180, 10.0);
        hall = new DomainHall("Hall 1", 50, "IMAX");
        session = new DomainSession(LocalDateTime.of(2024, 2, 12, 18, 0),
                movie.getId(), hall.getId(), 10.0);

        session.setId(1L);
    }

    @Test
    void testFindById_WhenExists() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        Optional<DomainSession> result = sessionService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindById_WhenNotExists() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<DomainSession> result = sessionService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        when(sessionRepository.save(any(DomainSession.class))).thenReturn(session);

        DomainSession savedSession = sessionService.save(session);

        assertNotNull(savedSession);
        assertEquals(1L, savedSession.getId());
    }

    @Test
    void testUpdate() {
        when(sessionRepository.save(any(DomainSession.class))).thenReturn(session);

        sessionService.update(session, 1L);
        assertEquals(1L, session.getId());
    }

    @Test
    void testDeleteById() {
        doNothing().when(sessionRepository).deleteById(1L);

        assertDoesNotThrow(() -> sessionService.deleteById(1L));
        verify(sessionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAll() {
        List<DomainSession> sessions = Arrays.asList(
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 18, 0),
                        movie.getId(), hall.getId(), 10.0),
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 21, 30),
                        movie.getId(), hall.getId(), 10.0)
        );

        when(sessionRepository.findAll()).thenReturn(sessions);

        List<DomainSession> result = sessionService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testEndTimeBySessionId() {
        LocalDateTime expectedEndTime = LocalDateTime.of(2024, 2, 12, 20, 0);
        when(sessionRepository.endTimeBySessionId(1L)).thenReturn(expectedEndTime);

        LocalDateTime endTime = sessionService.endTimeBySessionId(1L);

        assertNotNull(endTime);
        assertEquals(expectedEndTime, endTime);
    }

    @Test
    void testGetAllSessionsFromHall() {
        List<DomainSession> hallSessions = Arrays.asList(
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 18, 0),
                        movie.getId(), hall.getId(), 10.0),
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 21, 30),
                        movie.getId(), hall.getId(), 10.0)
        );

        when(sessionRepository.getAllSessionsFromHall(1L)).thenReturn(hallSessions);

        List<DomainSession> result = sessionService.getAllSessionsFromHall(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllSessionsFromMovie() {
        List<DomainSession> movieSessions = Arrays.asList(
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 18, 0),
                        movie.getId(), hall.getId(), 10.0),
                session = new DomainSession(LocalDateTime.of(2024, 2, 12, 21, 30),
                        movie.getId(), hall.getId(), 10.0)
        );

        when(sessionRepository.getAllSessionsFromMovie(1L)).thenReturn(movieSessions);

        List<DomainSession> result = sessionService.getAllSessionsFromMovie(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
