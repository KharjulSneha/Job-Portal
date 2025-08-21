package job.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerUpload {
    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private String phoneNo;
    private String password;
//for only jobseeker
    private String preferences;
    private String profileImagePath;
    private String resumePdfPath;
}
