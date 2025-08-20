package job.portal.controllers;

import job.portal.dto.EmployerDTO;
import job.portal.entities.Employer;
import job.portal.mappers.EmployerMapper;
import job.portal.services.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping
    public List<EmployerDTO> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDTO> getEmployerById(@PathVariable Long id) {
        Optional<EmployerDTO> employer = employerService.getEmployerById(id);
        return employer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployerDTO> createEmployer(@Valid @RequestBody EmployerDTO employerDTO) {
        Employer employer = EmployerMapper.toEntity(employerDTO);
        EmployerDTO created = employerService.createEmployer(employer);
        return ResponseEntity.ok(created);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerDTO> updateEmployer(@PathVariable Long id, @Valid @RequestBody Employer updatedEmployer) {
        Optional<EmployerDTO> updated = employerService.updateEmployer(id, updatedEmployer);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        if (employerService.deleteEmployer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
