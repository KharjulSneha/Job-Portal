package job.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDTO {

    Long userId;
    private String username;
    private String email;
    private String phoneNo;
    private String roleName;
}
