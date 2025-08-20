package job.portal.repositories;

import job.portal.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    Optional<Employer> findById(Long id);

    boolean existsByAadharNumber(String aadharNumber);

    Optional<Employer> findByAadharNumber(String aadharNumber);
}
