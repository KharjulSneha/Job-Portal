package job.portal.services;

import job.portal.dto.AdminDTO;
import job.portal.entities.Admin;
import job.portal.mappers.AdminMapper;
import job.portal.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<AdminDTO> getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(value -> ResponseEntity.ok(AdminMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<AdminDTO> createAdmin(AdminDTO adminDTO) {
        Admin admin = AdminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(AdminMapper.toDTO(savedAdmin));
    }

    public ResponseEntity<AdminDTO> updateAdmin(Long id, AdminDTO adminDTO) {
        return adminRepository.findById(id).map(existingAdmin -> {
            existingAdmin.setAdminName(adminDTO.getAdminName());
            existingAdmin.setEmail(adminDTO.getEmail());
            existingAdmin.setPasswordSalt(adminDTO.getPasswordSalt());
            existingAdmin.setPasswordHash(adminDTO.getPasswordHash());
            Admin updatedAdmin = adminRepository.save(existingAdmin);
            return ResponseEntity.ok(AdminMapper.toDTO(updatedAdmin));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

