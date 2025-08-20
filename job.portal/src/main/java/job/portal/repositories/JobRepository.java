package job.portal.repositories;

import job.portal.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Custom query methods (optional)
    List<Job> findByIsActiveTrue();

    List<Job> findByEmployerId(Long employerId);

    List<Job> findByLocationContainingIgnoreCase(String location);

    List<Job> findByTitleContainingIgnoreCase(String title);
}

