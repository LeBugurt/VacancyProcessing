package bugurt.vacancy.web.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class VacancyFilter {
    String country;
    String region;
    String city;
    String status;
    String grade;
    String position;
    List<String> skills;
    long salaryFrom;
    long salaryTo;
}
