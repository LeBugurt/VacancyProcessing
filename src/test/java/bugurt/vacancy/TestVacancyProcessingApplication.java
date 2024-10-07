package bugurt.vacancy;

import org.springframework.boot.SpringApplication;

public class TestVacancyProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.from(VacancyProcessingApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
