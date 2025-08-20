package job.portal.services;

import job.portal.dto.EmployerDTO;
import job.portal.entities.Employer;
import job.portal.mappers.EmployerMapper;
import job.portal.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;

    public List<EmployerDTO> getAllEmployers() {
        return employerRepository.findAll()
                .stream()
                .map(EmployerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployerDTO> getEmployerById(Long id) {
        return employerRepository.findById(id)
                .map(EmployerMapper::toDTO);
    }

    public EmployerDTO createEmployer(Employer employer) {

        Employer saved = employerRepository.save(employer);
        return EmployerMapper.toDTO(saved);
    }

    public Optional<EmployerDTO> updateEmployer(Long id, Employer updatedData) {
        return employerRepository.findById(id)
                .map(existing -> {
                    existing.setUserName(updatedData.getUserName());
                    existing.setDepartment(updatedData.getDepartment());
                    existing.setProfilePhoto(updatedData.getProfilePhoto());
                    existing.setAadharNumber(updatedData.getAadharNumber());
                    existing.setVerified(updatedData.isVerified());
                    existing.setApproved(updatedData.isApproved());
                    Employer updated = employerRepository.save(existing);
                    return EmployerMapper.toDTO(updated);
                });
    }

    public boolean deleteEmployer(Long id) {
        if (employerRepository.existsById(id)) {
            employerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
