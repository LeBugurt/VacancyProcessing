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
@Table(name = "tb_hr_favourites")
public class Favourites extends EntityId {

    @Column(name = "id_hr")
    private UUID hrId;

    @Column(name = "id_vacancy")
    private UUID vacancyId;
}
