package job.portal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder // Optional: allows building objects using the builder pattern
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employer_id", nullable = false)
    private Long employerId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "skills_required")
    private String skillsRequired;

    @Column(name = "experience_required")
    private String experienceRequired;

    @Column(name = "salary_range")
    private String salaryRange;

    private String location;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "is_active")
    private Boolean isActive;
}
