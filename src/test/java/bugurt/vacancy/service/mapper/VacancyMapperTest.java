package bugurt.vacancy.service.mapper;

import bugurt.vacancy.model.Location;
import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.web.Dto.VacancyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VacancyMapperTest {
    private VacancyMapper vacancyMapper;

    @BeforeEach
    void setUp() {
        vacancyMapper = new VacancyMapper();
    }

    @Test
    void testVacancyToVacancyDto() {
        // Создание тестовых данных
        Vacancy vacancy = new Vacancy();
        vacancy.setId(UUID.randomUUID());
        vacancy.setPosition("Software Engineer");
        vacancy.setCompany("Tech Company");
        vacancy.setSalary((long)100000);
        vacancy.setCurrency("USD");
        vacancy.setDescription("Develop software solutions.");
        vacancy.setExperience("2-5 years");
        vacancy.setStatus("Open");
        vacancy.setPublicationDate(LocalDate.parse("2024-10-07"));
        vacancy.setCreatedAt(LocalDateTime.parse("2023-09-01T10:15:00"));
        vacancy.setUpdatedAt(LocalDateTime.parse("2023-09-01T10:15:00"));
        vacancy.setGrade("Mid");

        // Преобразование Vacancy в VacancyDto
        VacancyDto vacancyDto = vacancyMapper.vacancyToVacancyDto(vacancy);

        // Проверка значений в VacancyDto
        assertEquals(vacancy.getId(), vacancyDto.getId());
        assertEquals(vacancy.getPosition(), vacancyDto.getPosition());
        assertEquals(vacancy.getCompany(), vacancyDto.getCompany());
        assertEquals(vacancy.getSalary(), vacancyDto.getSalary());
        assertEquals(vacancy.getCurrency(), vacancyDto.getCurrency());
        assertEquals(vacancy.getDescription(), vacancyDto.getDescription());
        assertEquals(vacancy.getExperience(), vacancyDto.getExperience());
        assertEquals(vacancy.getStatus(), vacancyDto.getStatus());
        assertEquals(vacancy.getPublicationDate(), vacancyDto.getPublication_date());
        assertEquals(vacancy.getCreatedAt(), vacancyDto.getCreatedAt());
        assertEquals(vacancy.getUpdatedAt(), vacancyDto.getUpdatedAt());
        assertEquals(vacancy.getGrade(), vacancyDto.getGrade());
    }

    @Test
    void testLocationToVacancyDto() {
        // Создание тестовых данных
        Location location = new Location();
        location.setCountry("USA");
        location.setCity("New York");
        location.setRegion("NY");

        VacancyDto vacancyDto = new VacancyDto();

        // Преобразование Location в VacancyDto
        vacancyMapper.locationToVacancyDto(location, vacancyDto);

        // Проверка значений в VacancyDto
        assertEquals(location.getCountry(), vacancyDto.getCountry());
        assertEquals(location.getCity(), vacancyDto.getCity());
        assertEquals(location.getRegion(), vacancyDto.getRegion());
    }
}