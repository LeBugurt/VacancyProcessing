package bugurt.vacancy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_vacancy")
@Getter
@Setter
public class Vacancy extends EntityId {
    private String position;

    private String company;

    private Long salary;

    private String currency;

    private String description;

    private String experience;

    private String grade;

    private String status;

    private LocalDate publicationDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID hrId;

    @OneToMany(mappedBy = "vacancyId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VacancySkill> vacancySkills;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id_vacancy")
    private VacancyLocation vacancyLocation;
}
