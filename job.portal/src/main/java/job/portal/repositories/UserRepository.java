package job.portal.repositories;

import job.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByRoleRoleId(int roleId);


//    Optional<Object> findById(Long userId);
}
