package job.portal.repositories;

import job.portal.entities.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleNameIgnoreCase(String roleName);

    Optional<Object> findByRoleName(String roleName);
}
