package job.portal.services;

import job.portal.dto.RoleDTO;
import job.portal.entities.Role;
import job.portal.exceptions.RoleNotFoundException;
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

    private final List<String> allowedRoles = Arrays.asList("Admin", "Employer", "Job Seeker");

    @PostMapping
    public Role createRole(Role role) {
        if (roleRepository.count() >= 3) {
            throw new RoleValidationException("Maximum of 3 roles allowed: Admin, Employer, Job Seeker.");
        }

        if (!allowedRoles.contains(role.getRoleName())) {
            throw new RoleValidationException("Only Admin, Employer, or Job Seeker roles are allowed.");
        }

        if (roleRepository.findByRoleNameIgnoreCase(role.getRoleName()).isPresent()) {
            throw new RoleValidationException("Role '" + role.getRoleName() + "' already exists.");
        }

        switch (role.getRoleName()) {
            case "Admin" -> role.setRoleId(1L);
            case "Employer" -> role.setRoleId(2L);
            case "Job Seeker" -> role.setRoleId(3L);
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
                .orElseThrow(() -> new RuntimeException("Only " + roleRepository.count() + " roles are available."));


    }

    @DeleteMapping
    public boolean deleteRole(int id){
        if (!roleRepository.existsById(id)) {
            long totalRoles = roleRepository.count();
            throw new RoleNotFoundException("Role with id " + id + " and above not found. Only " + totalRoles + " roles are available.");
        }
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
