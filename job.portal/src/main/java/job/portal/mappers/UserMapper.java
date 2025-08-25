package job.portal.mappers;

import job.portal.dto.UserDetailsDTO;
import job.portal.dto.UserRegisterDTO;
import job.portal.entities.Role;
import job.portal.entities.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserRegisterDTO
                                        dto, Role role) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoneNo(dto.getPhoneNo());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(role);
        return user;
    }

    public static UserDetailsDTO toDTO(User user) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhoneNo(user.getPhoneNo());
        dto.setRoleName(user.getRole().getRoleName());
        dto.setRoleName(user.getRole() != null ? user.getRole().getRoleName() : null);

//        if (user.getJobSeeker() != null) {
//            dto.setPreferences(user.getJobSeeker().getPreferences());
//        }
        return dto;
    }


}
