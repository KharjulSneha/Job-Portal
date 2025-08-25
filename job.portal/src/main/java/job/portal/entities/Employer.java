package job.portal.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String department;

    private String profilePhoto;

    @Column(nullable = false, unique = true)
    private String aadharNumber;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private boolean verified;

}
