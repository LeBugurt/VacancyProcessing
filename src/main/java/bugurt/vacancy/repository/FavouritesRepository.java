package bugurt.vacancy.repository;

import bugurt.vacancy.model.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, UUID> {
    List<Favourites> findByHrId(UUID id);
}
