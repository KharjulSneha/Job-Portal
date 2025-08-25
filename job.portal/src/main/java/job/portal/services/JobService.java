package job.portal.services;

import job.portal.dto.JobDTO;
import job.portal.entities.Job;
import job.portal.mappers.JobMapper;
import job.portal.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    // Get all jobs
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get job by ID
    public Optional<JobDTO> getJobById(Long id) {
        return jobRepository.findById(id)
                .map(JobMapper::toDTO);
    }

    // Create new job
    public JobDTO createJob(JobDTO dto) {
        Job job = JobMapper.toEntity(dto);
        Job saved = jobRepository.save(job);
        return JobMapper.toDTO(saved);
    }

    // Update job
    public Optional<JobDTO> updateJob(Long id, JobDTO dto) {
        return jobRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setDescription(dto.getDescription());
                    existing.setSkillsRequired(dto.getSkillsRequired());
                    existing.setExperienceRequired(dto.getExperienceRequired());
                    existing.setSalaryRange(dto.getSalaryRange());
                    existing.setLocation(dto.getLocation());
                    existing.setJobType(dto.getJobType());
                    existing.setPostedAt(dto.getPostedAt());
                    existing.setEndAt(dto.getEndAt());
                    existing.setIsActive(dto.getIsActive());
                    Job updated = jobRepository.save(existing);
                    return JobMapper.toDTO(updated);
                });
    }

    // Delete job
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
