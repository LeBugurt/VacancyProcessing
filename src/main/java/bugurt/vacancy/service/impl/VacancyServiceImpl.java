package bugurt.vacancy.service.impl;

import bugurt.vacancy.model.Location;
import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.repository.VacancyRepository;
import bugurt.vacancy.repository.VacancySkillRepository;
import bugurt.vacancy.service.FavouritesService;
import bugurt.vacancy.service.VacancyLocationService;
import bugurt.vacancy.service.VacancyService;
import bugurt.vacancy.service.mapper.VacancyMapper;
import bugurt.vacancy.web.Dto.VacancyDto;
import bugurt.vacancy.web.Dto.VacancyFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private VacancyRepository vacancyRepository;
    private VacancySkillRepository vacancySkillRepository;
    private VacancyMapper vacancyMapper;
    private VacancyLocationService vacancyLocationService;
    private FavouritesService favouritesService;

    private List<VacancyDto> vacancyDtoList(List<Vacancy> vacancies) {
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            VacancyDto vacancyDto = vacancyMapper.vacancyToVacancyDto(vacancy);
            vacancyDto.setSkills(vacancySkillRepository.findAllById(vacancy.getId()));
            Location location = vacancyLocationService.getVacancyLocation(vacancy.getId());
            vacancyMapper.locationToVacancyDto(location, vacancyDto);

            vacancyDtos.add(vacancyDto);
        }

        return vacancyDtos;
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAllByOrderByPublicationDateDesc();
        return vacancyDtoList(vacancies);
    }

    @Override
    public List<VacancyDto> getAllVacanciesOldFirst() {
        List<Vacancy> vacancies = vacancyRepository.findAllByOrderByPublicationDateAsc();
        return vacancyDtoList(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesByKeyWord(String keyword) {
        List<Vacancy> vacancies = vacancyRepository.findAllByKeyword(keyword);
        return vacancyDtoList(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesByTabOnlyMy(UUID userId) {
        List<Vacancy> vacancies = vacancyRepository.findAllByHrId(userId);
        return vacancyDtoList(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesByTabFavourites(UUID userId) {
        List<Vacancy> vacancies = favouritesService.getAllVacanciesByUser(userId);
        return vacancyDtoList(vacancies);
    }

    @Override
    public List<VacancyDto> searchVacanciesByFilter(VacancyFilter vacancyFilter) {
        List<Vacancy> vacancies = vacancyRepository.findVacanciesByFilters(vacancyFilter.getCountry(),
                vacancyFilter.getRegion(),
                vacancyFilter.getCity(),
                vacancyFilter.getStatus(),
                vacancyFilter.getGrade(),
                vacancyFilter.getPosition(),
                vacancyFilter.getSkills(),
                vacancyFilter.getSalaryFrom(),
                vacancyFilter.getSalaryTo());
        return vacancyDtoList(vacancies);
    }
}