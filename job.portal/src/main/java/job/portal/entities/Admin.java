package job.portal.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long adminId;

 @Column(nullable = false)
 private String adminName;

 @Column(nullable = false)
 private String passwordSalt;

 @Column(nullable = false)
 private String passwordHash;

 @Column(nullable = false, unique = true)
 private String email;
}
