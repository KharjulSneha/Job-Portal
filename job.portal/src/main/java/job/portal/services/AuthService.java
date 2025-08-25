package job.portal.services;

import job.portal.dto.UserLoginDTO;
import job.portal.entities.User;
import job.portal.exceptions.InvalidPasswordException;
import job.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String loginUser(UserLoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(loginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return "User not found. Please register first.";
        }

        User user = optionalUser.get();

        boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (passwordMatch) {
            return "Login successful!";
        } else {
            throw new InvalidPasswordException("Incorrect password. Please check your password.");
        }
    }
}
