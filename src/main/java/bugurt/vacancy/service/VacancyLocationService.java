package bugurt.vacancy.service;

import bugurt.vacancy.model.Location;

import java.util.Optional;
import java.util.UUID;

public interface VacancyLocationService {

    Location getVacancyLocation(UUID idVacancy);
}
