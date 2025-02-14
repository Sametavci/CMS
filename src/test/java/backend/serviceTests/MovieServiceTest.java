package backend.serviceTests;


import backend.application.services.MovieService;
import backend.domain.models.DomainMovie;
import backend.domain.ports.repositorys.IMovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private IMovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private DomainMovie movie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movie = new DomainMovie("Interstellar", "Sci-Fi", 180, 9.5);
    }

    @Test
    void testFindById_WhenExists() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Optional<DomainMovie> result = movieService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Interstellar", result.get().getTitle());
    }

    @Test
    void testFindById_WhenNotExists() {
        when(movieRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<DomainMovie> result = movieService.findById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        when(movieRepository.save(any(DomainMovie.class))).thenReturn(movie);

        DomainMovie savedMovie = movieService.save(movie);

        assertNotNull(savedMovie);
        assertEquals("Interstellar", savedMovie.getTitle());
    }

    @Test
    void testUpdate() {
        when(movieRepository.save(any(DomainMovie.class))).thenReturn(movie);

        movieService.update(movie, 1L);

        assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    void testDeleteById() {
        doNothing().when(movieRepository).deleteById(1L);

        assertDoesNotThrow(() -> movieService.deleteById(1L));

        verify(movieRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAll() {
        List<DomainMovie> movies = Arrays.asList(
                new DomainMovie("Inception", "Sci-Fi", 148, 9.0),
                new DomainMovie("The Dark Knight", "Action", 152, 9.0)
        );

        when(movieRepository.findAll()).thenReturn(movies);

        List<DomainMovie> result = movieService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
