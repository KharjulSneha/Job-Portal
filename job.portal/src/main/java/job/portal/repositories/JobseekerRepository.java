package job.portal.repositories;

import job.portal.entities.JobSeekers;
import job.portal.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobseekerRepository extends JpaRepository<JobSeekers, Integer> {
    Optional<JobSeekers> findByUser(Users user);
}
