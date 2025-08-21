package job.portal.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerResponse {
    private Long id;
    private String preferences;
    private Integer profileScore;
    private String profileImagePath;
    private String resumePdfPath;
    private String fullName;
    private String username;
    private String email;
    private String phoneNo;

}

