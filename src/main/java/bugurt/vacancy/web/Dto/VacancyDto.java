package bugurt.vacancy.web.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class VacancyDto {

    private UUID id;

    private String position;

    private String company;

    private long salary;

    private String currency;

    private String description;

    private String experience;

    private String country;

    private String city;

    private String region;

    private String grade;

    List<String> skills;

    private String status;

    private LocalDate publication_date;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}