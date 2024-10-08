package bugurt.vacancy.repository;

import bugurt.vacancy.model.VacancyLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VacancyLocationRepository extends JpaRepository<VacancyLocation, UUID> {
    Optional<VacancyLocation> findByVacancyId(UUID id);
}
