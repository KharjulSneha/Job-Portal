package job.portal.mappers;

import job.portal.dto.EmployerDTO;
import job.portal.entities.Employer;

public class EmployerMapper {

    public static EmployerDTO toDTO(Employer employer) {
        if (employer == null) return null;

        return EmployerDTO.builder()
                .userName(employer.getUserName())
                .department(employer.getDepartment())
                .profilePhoto(employer.getProfilePhoto())
                .aadharNumber(employer.getAadharNumber())
                .verified(employer.isVerified())
                .approved(employer.isApproved())
                .build();
    }

    public static Employer toEntity(EmployerDTO dto) {
        if (dto == null) return null;

        return Employer.builder()
                .userName(dto.getUserName())
                .department(dto.getDepartment())
                .profilePhoto(dto.getProfilePhoto())
                .aadharNumber(dto.getAadharNumber())
                .verified(dto.isVerified())
                .approved(dto.isApproved())
                .build();
    }
}
