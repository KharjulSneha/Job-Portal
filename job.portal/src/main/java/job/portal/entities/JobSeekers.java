package job.portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_seekers")
@Getter
@Setter
public class JobSeekers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferences;
    private int profileScore;

    private String profileImagePath;
    private String resumePdfPath;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
