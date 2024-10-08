package bugurt.vacancy.service.mapper;

import bugurt.vacancy.model.Location;
import bugurt.vacancy.model.Vacancy;
import bugurt.vacancy.model.VacancySkill;
import bugurt.vacancy.web.Dto.VacancyDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VacancyMapper {

    public VacancyDto vacancyToVacancyDto(Vacancy vacancy) {

        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setId(vacancy.getId());
        vacancyDto.setPosition(vacancy.getPosition());
        vacancyDto.setCompany(vacancy.getCompany());
        vacancyDto.setSalary(vacancy.getSalary());
        vacancyDto.setCurrency(vacancy.getCurrency());
        vacancyDto.setDescription(vacancy.getDescription());
        vacancyDto.setExperience(vacancy.getExperience());
        vacancyDto.setStatus(vacancy.getStatus());
        vacancyDto.setPublication_date(vacancy.getPublicationDate());
        vacancyDto.setCreatedAt(vacancy.getCreatedAt());
        vacancyDto.setUpdatedAt(vacancy.getUpdatedAt());
        vacancyDto.setGrade(vacancy.getGrade());

        return vacancyDto;
    }

    public void locationToVacancyDto(Location location, VacancyDto vacancyDto) {

        vacancyDto.setCountry(location.getCountry());
        vacancyDto.setCity(location.getCity());
        vacancyDto.setRegion(location.getRegion());;
    }
}
