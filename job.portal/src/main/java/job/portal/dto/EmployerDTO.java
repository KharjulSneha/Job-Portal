package job.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerDTO {

    private String userName;
    private String department;
    private String profilePhoto;
    private String aadharNumber;
    private boolean verified;
    private boolean approved;
}

