package job.portal.mappers;

import job.portal.dto.RoleDTO;
import job.portal.entities.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role){
        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

    public static Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }
}
