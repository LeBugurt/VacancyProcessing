package bugurt.vacancy.service;


import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.web.Dto.VacancyDto;
import bugurt.vacancy.web.Dto.VacancyFilter;

import java.util.List;
import java.util.UUID;

public interface VacancyService {

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getAllVacanciesOldFirst();

    List<VacancyDto> getVacanciesByKeyWord(String keyword);

    List<VacancyDto> getVacanciesByTabOnlyMy(UUID userId);

    List<VacancyDto> getVacanciesByTabFavourites(UUID userId);

    List<VacancyDto> searchVacanciesByFilter(VacancyFilter vacancyFilter);
}