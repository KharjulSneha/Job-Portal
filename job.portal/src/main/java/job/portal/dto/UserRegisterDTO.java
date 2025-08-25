package job.portal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private String roleName;
}
