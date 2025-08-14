package job.portal.controllers;

import job.portal.dto.RoleDTO;
import job.portal.entities.Role;
import job.portal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService ;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id){
        RoleDTO roleDTO = roleService.getRoleById(id);
        return roleDTO != null ? ResponseEntity.ok(roleDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id){
        return roleService.deleteRole(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
