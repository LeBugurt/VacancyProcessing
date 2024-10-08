package bugurt.vacancy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_hr")
@Getter
@Setter
public class HR extends EntityId{

    private String name;

    private String email;

    private String phone;
}
