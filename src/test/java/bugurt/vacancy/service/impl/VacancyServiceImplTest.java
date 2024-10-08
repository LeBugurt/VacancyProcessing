package bugurt.vacancy.service.impl;

import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.repository.VacancyRepository;
import bugurt.vacancy.repository.VacancySkillRepository;
import bugurt.vacancy.service.FavouritesService;
import bugurt.vacancy.service.VacancyLocationService;
import bugurt.vacancy.service.mapper.VacancyMapper;
import bugurt.vacancy.web.Dto.VacancyDto;
import bugurt.vacancy.web.Dto.VacancyFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VacancyServiceImplTest {

    @InjectMocks
    private VacancyServiceImpl vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private VacancySkillRepository vacancySkillRepository;

    @Mock
    private VacancyMapper vacancyMapper;

    @Mock
    private VacancyLocationService vacancyLocationService;

    @Mock
    private FavouritesService favouritesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVacanciesByKeyWord() {
        String keyword = "Java";
        Vacancy vacancy = new Vacancy();
        List<Vacancy> vacancies = Arrays.asList(vacancy);
        when(vacancyRepository.findAllByKeyword(keyword)).thenReturn(vacancies);

        VacancyDto vacancyDto = new VacancyDto();
        when(vacancyMapper.vacancyToVacancyDto(vacancy)).thenReturn(vacancyDto);

        List<VacancyDto> result = vacancyService.getVacanciesByKeyWord(keyword);

        assertEquals(1, result.size());
        verify(vacancyRepository).findAllByKeyword(keyword);
    }

    @Test
    void getVacanciesByTabOnlyMy() {
        UUID userId = UUID.randomUUID();
        Vacancy vacancy = new Vacancy();
        List<Vacancy> vacancies = Arrays.asList(vacancy);
        when(vacancyRepository.findAllByHrId(userId)).thenReturn(vacancies);

        VacancyDto vacancyDto = new VacancyDto();
        when(vacancyMapper.vacancyToVacancyDto(vacancy)).thenReturn(vacancyDto);

        List<VacancyDto> result = vacancyService.getVacanciesByTabOnlyMy(userId);

        assertEquals(1, result.size());
        verify(vacancyRepository).findAllByHrId(userId);
    }

    @Test
    void getVacanciesByTabFavourites() {
        UUID userId = UUID.randomUUID();
        Vacancy vacancy = new Vacancy();
        List<Vacancy> vacancies = Arrays.asList(vacancy);
        when(favouritesService.getAllVacanciesByUser(userId)).thenReturn(vacancies);

        VacancyDto vacancyDto = new VacancyDto();
        when(vacancyMapper.vacancyToVacancyDto(vacancy)).thenReturn(vacancyDto);

        List<VacancyDto> result = vacancyService.getVacanciesByTabFavourites(userId);

        assertEquals(1, result.size());
        verify(favouritesService).getAllVacanciesByUser(userId);
    }

    @Test
    void searchVacanciesByFilter() {
        VacancyFilter filter = new VacancyFilter();
        Vacancy vacancy = new Vacancy();
        List<Vacancy> vacancies = Arrays.asList(vacancy);
        when(vacancyRepository.findVacanciesByFilters(
                filter.getCountry(), filter.getRegion(), filter.getCity(),
                filter.getStatus(), filter.getGrade(), filter.getPosition(),
                filter.getSkills(), filter.getSalaryFrom(), filter.getSalaryTo()))
                .thenReturn(vacancies);

        VacancyDto vacancyDto = new VacancyDto();
        when(vacancyMapper.vacancyToVacancyDto(vacancy)).thenReturn(vacancyDto);

        List<VacancyDto> result = vacancyService.searchVacanciesByFilter(filter);

        assertEquals(1, result.size());
        verify(vacancyRepository).findVacanciesByFilters(
                filter.getCountry(), filter.getRegion(), filter.getCity(),
                filter.getStatus(), filter.getGrade(), filter.getPosition(),
                filter.getSkills(), filter.getSalaryFrom(), filter.getSalaryTo());
    }

    @Test
    void getAllVacanciesOldFirst() {
        Vacancy vacancy1 = new Vacancy();
        Vacancy vacancy2 = new Vacancy();
        List<Vacancy> vacancies = Arrays.asList(vacancy1, vacancy2);
        when(vacancyRepository.findAllByOrderByPublicationDateAsc()).thenReturn(vacancies);

        VacancyDto vacancyDto1 = new VacancyDto();
        VacancyDto vacancyDto2 = new VacancyDto();
        when(vacancyMapper.vacancyToVacancyDto(vacancy1)).thenReturn(vacancyDto1);
        when(vacancyMapper.vacancyToVacancyDto(vacancy2)).thenReturn(vacancyDto2);

        List<VacancyDto> result = vacancyService.getAllVacanciesOldFirst();

        assertEquals(2, result.size());
        verify(vacancyRepository).findAllByOrderByPublicationDateAsc();
    }
}