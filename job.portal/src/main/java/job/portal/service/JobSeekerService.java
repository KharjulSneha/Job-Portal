package job.portal.service;

import job.portal.dto.JobSeekerUpload;
import job.portal.entities.JobSeekers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobSeekerService {
    JobSeekers createJobSeeker(JobSeekers jobSeeker);
    JobSeekerUpload saveProfileFiles(Integer id, List<MultipartFile> profileImages, List<MultipartFile> resumePdfs);

}
