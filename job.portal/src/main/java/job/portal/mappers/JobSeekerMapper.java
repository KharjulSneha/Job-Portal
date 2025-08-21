package job.portal.mappers;

import job.portal.dto.JobSeekerResponse;
import job.portal.entities.JobSeekers;

public class JobSeekerMapper {

    public static JobSeekerResponse toDto(JobSeekers entity) {
        if (entity == null) return null;
        JobSeekerResponse dto = new JobSeekerResponse();
        dto.setId(entity.getId());
        dto.setPreferences(entity.getPreferences());
        dto.setProfileScore(entity.getProfileScore());
        dto.setProfileImagePath(entity.getProfileImagePath());
        dto.setResumePdfPath(entity.getResumePdfPath());

        if (entity.getUser() != null) {
            dto.setFullName(entity.getUser().getFullName());
            dto.setUsername(entity.getUser().getUsername());
            dto.setEmail(entity.getUser().getEmail());
            dto.setPhoneNo(entity.getUser().getPhoneNo());
        }
        return dto;
    }
}
