package job.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private Long adminId;
    private String adminName;
    private String passwordSalt;
    private String passwordHash;
    private String email;
}
