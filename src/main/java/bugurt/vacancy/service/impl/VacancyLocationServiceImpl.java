package bugurt.vacancy.service.impl;

import bugurt.vacancy.exception.ResourceNotFoundException;
import bugurt.vacancy.model.Location;
import bugurt.vacancy.model.VacancyLocation;
import bugurt.vacancy.repository.LocationRepository;
import bugurt.vacancy.repository.VacancyLocationRepository;
import bugurt.vacancy.service.VacancyLocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VacancyLocationServiceImpl implements VacancyLocationService {

    private LocationRepository locationRepository;

    private VacancyLocationRepository vacancyLocationRepository;

    @Override
    public Location getVacancyLocation(UUID idVacancy) {
        VacancyLocation vacancyLocation = vacancyLocationRepository.findByVacancyId(idVacancy)
                .orElseThrow(() -> new ResourceNotFoundException("VacancyLocation not found with id: " + idVacancy));

        return locationRepository.findById(vacancyLocation.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + vacancyLocation.getLocationId()));
    }
}
