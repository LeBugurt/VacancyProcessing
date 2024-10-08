package bugurt.vacancy.service.impl;

import bugurt.vacancy.exception.ResourceNotFoundException;
import bugurt.vacancy.model.Favourites;
import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.repository.FavouritesRepository;
import bugurt.vacancy.repository.VacancyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FavouritesServiceImplTest {
    private FavouritesRepository favouritesRepository;
    private VacancyRepository vacancyRepository;
    private FavouritesServiceImpl favouritesService;

    @BeforeEach
    void setUp() {
        favouritesRepository = mock(FavouritesRepository.class);
        vacancyRepository = mock(VacancyRepository.class);
        favouritesService = new FavouritesServiceImpl(favouritesRepository, vacancyRepository);
    }

    @Test
    void testGetAllVacanciesByUser_Success() {
        UUID userId = UUID.randomUUID();
        UUID vacancyId1 = UUID.randomUUID();
        UUID vacancyId2 = UUID.randomUUID();

        List<Favourites> favouritesList = new ArrayList<>();
        Favourites favourites1 = new Favourites();
        favourites1.setVacancyId(vacancyId1);
        favourites1.setHrId(userId);
        favouritesList.add(favourites1);
        Favourites favourites2 = new Favourites();
        favourites2.setVacancyId(vacancyId1);
        favourites2.setHrId(userId);
        favouritesList.add(favourites2);

        when(favouritesRepository.findByHrId(userId)).thenReturn(favouritesList);
        when(vacancyRepository.findById(vacancyId1)).thenReturn(Optional.of(new Vacancy()));
        when(vacancyRepository.findById(vacancyId2)).thenReturn(Optional.of(new Vacancy()));

        List<Vacancy> vacancies = favouritesService.getAllVacanciesByUser(userId);

        assertNotNull(vacancies);
        assertEquals(2, vacancies.size());
        verify(favouritesRepository, times(1)).findByHrId(userId);
        verify(vacancyRepository, times(2)).findById(any(UUID.class));
    }

    @Test
    void testGetAllVacanciesByUser_VacancyNotFound() {
        UUID userId = UUID.randomUUID();
        UUID vacancyId = UUID.randomUUID();

        List<Favourites> favouritesList = new ArrayList<>();
        Favourites favourites = new Favourites();
        favourites.setVacancyId(vacancyId);
        favourites.setHrId(userId);
        favouritesList.add(favourites);

        when(favouritesRepository.findByHrId(userId)).thenReturn(favouritesList);
        when(vacancyRepository.findById(vacancyId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            favouritesService.getAllVacanciesByUser(userId);
        });

        assertEquals("Vacancy not found with id: " + vacancyId, exception.getMessage());
        verify(favouritesRepository, times(1)).findByHrId(userId);
        verify(vacancyRepository, times(1)).findById(vacancyId);
    }

}