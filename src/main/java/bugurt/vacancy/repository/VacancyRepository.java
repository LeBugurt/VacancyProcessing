package bugurt.vacancy.repository;

import bugurt.vacancy.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, UUID>, JpaSpecificationExecutor<Vacancy> {

    List<Vacancy> findAllByOrderByPublicationDateDesc();

    List<Vacancy> findAllByOrderByPublicationDateAsc();

    List<Vacancy> findAllByHrId(UUID hrId);

    @Query("SELECT v FROM Vacancy v WHERE " +
            "LOWER(v.position) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.currency) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.experience) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.grade) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(v.status) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Vacancy> findAllByKeyword(@Param("keyword") String keyword);

    @Query("""
    SELECT v FROM Vacancy v 
    LEFT JOIN VacancyLocation vl ON v.id = vl.vacancyId 
    LEFT JOIN Location l ON vl.locationId = l.id 
    LEFT JOIN VacancySkill vs ON v.id = vs.vacancyId
    WHERE (:country IS NULL OR l.country = :country)
    AND (:region IS NULL OR l.region = :region)
    AND (:city IS NULL OR l.city = :city)
    AND (:status IS NULL OR v.status = :status)
    AND (:grade IS NULL OR v.grade = :grade)
    AND (:position IS NULL OR v.position LIKE %:position%)
    AND (:salaryFrom IS NULL OR v.salary >= :salaryFrom)
    AND (:salaryTo IS NULL OR v.salary <= :salaryTo)
    AND (:skills IS NULL OR vs.skill IN :skills)
""")
    List<Vacancy> findVacanciesByFilters(
            @Param("country") String country,
            @Param("region") String region,
            @Param("city") String city,
            @Param("status") String status,
            @Param("grade") String grade,
            @Param("position") String position,
            @Param("skills") List<String> skills,
            @Param("salaryFrom") Long salaryFrom,
            @Param("salaryTo") Long salaryTo
    );
}
