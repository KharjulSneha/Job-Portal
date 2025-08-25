package job.portal.services;

import jakarta.persistence.EntityNotFoundException;
import job.portal.dto.UserDetailsDTO;
import job.portal.dto.UserRegisterDTO;
import job.portal.entities.Role;
import job.portal.entities.User;
import job.portal.exceptions.RoleNotFoundException;
import job.portal.exceptions.UserNotFoundException;
import job.portal.mappers.UserMapper;
import job.portal.repositories.RoleRepository;
import job.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(UserRegisterDTO registrationDto) {

        Role role = (Role) roleRepository.findByRoleName(registrationDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setPhoneNo(registrationDto.getPhoneNo());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(role);

        return userRepository.save(user);
    }

    public List<UserDetailsDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(u -> new UserDetailsDTO(
                        u.getUserId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getPhoneNo(),
                        u.getRole().getRoleName()
                ))
                .toList();
    }

    public UserDetailsDTO getUserById(Integer id) {
        return userRepository.findById(Long.valueOf(id))
                .map(UserMapper::toDTO)
                .orElseThrow(()->new RuntimeException("User Not Found"));
    }

    public void deleteUserById(Integer id) {

        Long userId = Long.valueOf(id);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(userId);

    }


    public UserDetailsDTO updateUser(Integer id, UserDetailsDTO updateDTO) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());

        Role role = (Role) roleRepository.findByRoleName(updateDTO.getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + updateDTO.getRoleName()));
        user.setRole(role);

        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }


    public boolean isRoleAssignedToAnyUser(int roleId) {
        return userRepository.existsByRoleRoleId(roleId);
    }


}
