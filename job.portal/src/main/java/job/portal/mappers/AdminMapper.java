package job.portal.mappers;

import job.portal.dto.AdminDTO;
import job.portal.entities.Admin;

public class AdminMapper {

    public static AdminDTO toDTO(Admin admin) {
        if (admin == null) return null;
        return new AdminDTO(
                admin.getAdminId(),
                admin.getAdminName(),
                admin.getPasswordSalt(),
                admin.getPasswordHash(),
                admin.getEmail()
        );
    }

    public static Admin toEntity(AdminDTO adminDTO) {
        if (adminDTO == null) return null;
        return Admin.builder()
                .adminId(adminDTO.getAdminId())
                .adminName(adminDTO.getAdminName())
                .passwordSalt(adminDTO.getPasswordSalt())
                .passwordHash(adminDTO.getPasswordHash())
                .email(adminDTO.getEmail())
                .build();
    }
}
