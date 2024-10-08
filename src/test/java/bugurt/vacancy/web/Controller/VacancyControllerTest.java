package bugurt.vacancy.web.Controller;

import bugurt.vacancy.repository.VacancyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class VacancyControllerTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    void getAllVacancies() throws Exception {
        mockMvc.perform(get("/api/vacancies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getVacanciesByKeywords() throws Exception {
        String keyword = "Engineer";
        mockMvc.perform(get("/api/vacancies/search")
                        .param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getVacanciesByTabOnlyMine() throws Exception {
        UUID userId = UUID.fromString("c7037c7d-3764-402f-874f-ab24decee3ef");
        mockMvc.perform(get("/api/vacancies/onlyMine/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getVacanciesByTabFavourites() throws Exception {
        UUID userId = UUID.fromString("d34a423c-7c90-4773-8f26-566d9a0da843");
        mockMvc.perform(get("/api/vacancies/favourites/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getVacanciesByOldFirst() throws Exception {
        mockMvc.perform(get("/api/vacancies/sort/oldFirst"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getVacanciesFilter() throws Exception {
        String status = "Open";
        String grade = "Mid";
        int salaryFrom = 80000;
        int salaryTo = 120000;
        String skills = "SQL";

        mockMvc.perform(get("/api/vacancies/filter")
                        .param("status", status)
                        .param("grade", grade)
                        .param("salaryFrom", String.valueOf(salaryFrom))
                        .param("salaryTo", String.valueOf(salaryTo))
                        .param("skills", skills))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}