package bugurt.vacancy.service.impl;

import bugurt.vacancy.exception.ResourceNotFoundException;
import bugurt.vacancy.model.Location;
import bugurt.vacancy.model.VacancyLocation;
import bugurt.vacancy.repository.LocationRepository;
import bugurt.vacancy.repository.VacancyLocationRepository;
import bugurt.vacancy.service.impl.VacancyLocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyLocationServiceImplTest {

    private LocationRepository locationRepository;
    private VacancyLocationRepository vacancyLocationRepository;
    private VacancyLocationServiceImpl vacancyLocationService;

    @BeforeEach
    void setUp() {
        locationRepository = mock(LocationRepository.class);
        vacancyLocationRepository = mock(VacancyLocationRepository.class);
        vacancyLocationService = new VacancyLocationServiceImpl(locationRepository, vacancyLocationRepository);
    }

    @Test
    void testGetVacancyLocation_Success() {
        UUID vacancyId = UUID.randomUUID();
        UUID locationId = UUID.randomUUID();

        VacancyLocation vacancyLocation = new VacancyLocation();
        vacancyLocation.setLocationId(locationId);

        Location location = new Location();
        location.setId(locationId);
        location.setCity("Test City");

        when(vacancyLocationRepository.findByVacancyId(vacancyId)).thenReturn(Optional.of(vacancyLocation));
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        Location result = vacancyLocationService.getVacancyLocation(vacancyId);

        assertNotNull(result);
        assertEquals("Test City", result.getCity());

        verify(vacancyLocationRepository, times(1)).findByVacancyId(vacancyId);
        verify(locationRepository, times(1)).findById(locationId);
    }

    @Test
    void testGetVacancyLocation_VacancyLocationNotFound() {
        UUID vacancyId = UUID.randomUUID();

        when(vacancyLocationRepository.findByVacancyId(vacancyId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            vacancyLocationService.getVacancyLocation(vacancyId);
        });

        assertEquals("VacancyLocation not found with id: " + vacancyId, exception.getMessage());

        verify(vacancyLocationRepository, times(1)).findByVacancyId(vacancyId);
        verify(locationRepository, never()).findById(any());
    }

    @Test
    void testGetVacancyLocation_LocationNotFound() {
        UUID vacancyId = UUID.randomUUID();
        UUID locationId = UUID.randomUUID();

        VacancyLocation vacancyLocation = new VacancyLocation();
        vacancyLocation.setLocationId(locationId);

        when(vacancyLocationRepository.findByVacancyId(vacancyId)).thenReturn(Optional.of(vacancyLocation));
        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            vacancyLocationService.getVacancyLocation(vacancyId);
        });

        assertEquals("Location not found with id: " + locationId, exception.getMessage());

        verify(vacancyLocationRepository, times(1)).findByVacancyId(vacancyId);
        verify(locationRepository, times(1)).findById(locationId);
    }
}