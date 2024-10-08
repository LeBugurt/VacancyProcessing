package bugurt.vacancy.web.Controller;

import bugurt.vacancy.service.VacancyService;
import bugurt.vacancy.web.Dto.VacancyDto;
import bugurt.vacancy.web.Dto.VacancyFilter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }

    @GetMapping("/search")
    public ResponseEntity<List<VacancyDto>> getVacanciesByKeywords(@RequestParam String keyword) {
        return ResponseEntity.ok(vacancyService.getVacanciesByKeyWord(keyword));
    }

    @GetMapping("/onlyMine/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByTabOnlyMine(@PathVariable UUID id) {
        return ResponseEntity.ok(vacancyService.getVacanciesByTabOnlyMy(id));
    }

    @GetMapping("/favourites/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByTabFavourites(@PathVariable UUID id) {
        return ResponseEntity.ok(vacancyService.getVacanciesByTabFavourites(id));
    }

    @GetMapping("/sort/oldFirst")
    public ResponseEntity<List<VacancyDto>> getVacanciesByOldFirst() {
        return ResponseEntity.ok(vacancyService.getAllVacanciesOldFirst());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<VacancyDto>> getVacanciesFilter(@ParameterObject VacancyFilter filter) {
        return ResponseEntity.ok(vacancyService.searchVacanciesByFilter(filter));
    }
}
