package job.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
