package job.portal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {

    private Long id;
    private Long employerId;
    private String title;
    private String description;
    private String skillsRequired;
    private String experienceRequired;
    private String salaryRange;
    private String location;
    private String jobType;
    private LocalDateTime postedAt;
    private LocalDateTime endAt;
    private Boolean isActive;
}

