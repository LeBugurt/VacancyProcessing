package bugurt.vacancy.repository;

import bugurt.vacancy.model.VacancySkill;
import bugurt.vacancy.web.Dto.VacancyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VacancySkillRepository extends JpaRepository<VacancySkill, UUID> {
    @Query("SELECT DISTINCT a.skill from VacancySkill a where a.vacancyId = :idVacancy")
    List<String> findAllById(@Param("idVacancy")UUID idVacancy);
}
