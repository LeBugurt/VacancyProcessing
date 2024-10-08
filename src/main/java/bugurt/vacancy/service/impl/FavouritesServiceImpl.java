package bugurt.vacancy.service.impl;

import bugurt.vacancy.exception.ResourceNotFoundException;
import bugurt.vacancy.model.Favourites;
import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.repository.FavouritesRepository;
import bugurt.vacancy.repository.VacancyRepository;
import bugurt.vacancy.service.FavouritesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FavouritesServiceImpl implements FavouritesService {

    FavouritesRepository favouritesRepository;
    VacancyRepository vacancyRepository;

    @Override
    public List<Vacancy> getAllVacanciesByUser(UUID userId) {
        List<Favourites> favouritesVacancy = favouritesRepository.findByHrId(userId);
        List<Vacancy> vacancies = new ArrayList<>();
        for (Favourites favourite : favouritesVacancy) {
            Vacancy vacancy = vacancyRepository.findById(favourite.getVacancyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found with id: " + favourite.getVacancyId()));

            vacancies.add(vacancy);
        }
        return vacancies;
    }
}
