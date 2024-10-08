package bugurt.vacancy.service;

import bugurt.vacancy.model.Vacancy;

import java.util.List;
import java.util.UUID;

public interface FavouritesService {

    List<Vacancy> getAllVacanciesByUser(UUID userId);
}
