package bugurt.vacancy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_vacancy_location")
public class VacancyLocation extends EntityId {

    @Column(name = "id_vacancy", nullable = false)
    private UUID vacancyId;

    @Column(name = "id_location", nullable = false)
    private UUID locationId;

    @ManyToOne
    @JoinColumn(name = "id_location", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;
}
