package job.portal.service;

import job.portal.dto.JobSeekerUpload;
import job.portal.entities.JobSeekers;
import job.portal.exceptions.FileStorageException;
import job.portal.exceptions.ResourceNotFoundException;
import job.portal.repositories.JobseekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobseekerRepository jobSeekerRepository;
    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public JobSeekerUpload saveProfileFiles(Integer id, List<MultipartFile> profileImages, List<MultipartFile> resumePdfs) {
        JobSeekers js = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobSeeker not found with id: " + id));

        JobSeekerUpload uploadedFiles = new JobSeekerUpload();
        uploadedFiles.setId(id);
        try {
            //for profile image saving
            if (profileImages != null && !profileImages.isEmpty()) {
                MultipartFile image = profileImages.get(0);
                if (!image.isEmpty()) {
                    String profilePath = fileStorageService.storeFile(image, "profile", Long.valueOf(id));
                    js.setProfileImagePath(profilePath);
                    uploadedFiles.setProfileImagePath(profilePath);
                }
            }
            // for file saving
            if (resumePdfs != null && !resumePdfs.isEmpty()) {
                MultipartFile pdf = resumePdfs.get(0);
                if (!pdf.isEmpty()) {
                    String resumePath = fileStorageService.storeFile(pdf, "resume", Long.valueOf(id));
                    js.setResumePdfPath(resumePath);
                    uploadedFiles.setResumePdfPath(resumePath);
                }
            }
//persist saving
            jobSeekerRepository.save(js);
            return uploadedFiles;
        } catch (IOException e) {
            throw new FileStorageException("Failed to store files: " + e.getMessage());
        }
    }
    @Override
    public JobSeekers createJobSeeker(JobSeekers jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }
}
