package bugurt.vacancy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_vacancy_skill")
public class VacancySkill extends EntityId {

    private String skill;

    @Column(name = "vacancy_id")
    private UUID vacancyId;
}
