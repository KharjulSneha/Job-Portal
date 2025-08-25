package job.portal.mappers;

import job.portal.dto.JobDTO;
import job.portal.entities.Job;

public class JobMapper {

    public static JobDTO toDTO(Job job) {
        if (job == null) return null;

        return JobDTO.builder()
                .id(job.getId())
                .employerId(job.getEmployerId())
                .title(job.getTitle())
                .description(job.getDescription())
                .skillsRequired(job.getSkillsRequired())
                .experienceRequired(job.getExperienceRequired())
                .salaryRange(job.getSalaryRange())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .postedAt(job.getPostedAt())
                .endAt(job.getEndAt())
                .isActive(job.getIsActive())
                .build();
    }

    public static Job toEntity(JobDTO dto) {
        if (dto == null) return null;

        return Job.builder()
                .id(dto.getId())
                .employerId(dto.getEmployerId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .skillsRequired(dto.getSkillsRequired())
                .experienceRequired(dto.getExperienceRequired())
                .salaryRange(dto.getSalaryRange())
                .location(dto.getLocation())
                .jobType(dto.getJobType())
                .postedAt(dto.getPostedAt())
                .endAt(dto.getEndAt())
                .isActive(dto.getIsActive())
                .build();
    }
}
