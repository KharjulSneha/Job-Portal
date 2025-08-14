package job.portal.services;

import job.portal.dto.RoleDTO;
import job.portal.entities.Role;
import job.portal.mappers.RoleMapper;
import job.portal.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import job.portal.exceptions.RoleValidationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Map;


import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private RoleService roleService;

    // Allowed roles
    private final List<String> allowedRoles = Arrays.asList("Admin", "Employer", "Job Seeker");

    @PostMapping
    public Role createRole(Role role) {
        // Restrict total count to 3
        if (roleRepository.count() >= 3) {
            throw new RoleValidationException("Maximum of 3 roles allowed: Admin, Employer, Job Seeker.");
        }

        // Validate role name
        if (!allowedRoles.contains(role.getRoleName())) {
            throw new RoleValidationException("Only Admin, Employer, or Job Seeker roles are allowed.");
        }

        // Check if role already exists
        if (roleRepository.findByRoleNameIgnoreCase(role.getRoleName()).isPresent()) {
            throw new RoleValidationException("Role '" + role.getRoleName() + "' already exists.");
        }

        // Set fixed ID based on role name
        switch (role.getRoleName()) {
            case "Admin" -> role.setRoleId(1);
            case "Employer" -> role.setRoleId(2);
            case "Job Seeker" -> role.setRoleId(3);
        }

        return roleRepository.save(role);
    }

    @GetMapping
    public List<RoleDTO> getAllRoles(){
        List<Role> roles = roleRepository.findAll();

        List<RoleDTO> roleDTOList = roles.stream()
                .map(r -> new RoleDTO(r.getRoleId(), r.getRoleName()))
                .toList();

        return roleDTOList;
    }

    @GetMapping
    public RoleDTO getRoleById(Integer id){
        return roleRepository.findById(id)
                .map(RoleMapper::toDTO)
                .orElse(null);

    }

    @DeleteMapping
    public boolean deleteRole(int id){
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
