package job.portal.controllers;

import job.portal.dto.JobSeekerResponse;
import job.portal.dto.JobSeekerUpload;
import job.portal.entities.JobSeekers;
import job.portal.mappers.JobSeekerMapper;
import job.portal.repositories.JobseekerRepository;
import job.portal.repositories.UsersRepository;
import job.portal.utils.PasswordUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {

    private final JobseekerRepository jobSeekerRepository;
    private final UsersRepository userRepository;
    public JobSeekerController(JobseekerRepository jobSeekerRepository, UsersRepository userRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.userRepository = userRepository;
    }
    //Get ID
    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerResponse> getJobSeekerById(@PathVariable Integer id) {
        JobSeekers jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jobseeker not found with id " + id));

        return ResponseEntity.ok(JobSeekerMapper.toDto(jobSeeker));
    }

    //Update any field (here jobseeker)
    @PutMapping("/update")
    public ResponseEntity<JobSeekerResponse> updateJobSeeker(@RequestBody JobSeekerUpload dto) {
        JobSeekers jobSeeker = jobSeekerRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Jobseeker not found with id " + dto.getId()));
        if (dto.getPreferences() != null) jobSeeker.setPreferences(dto.getPreferences());
        if (dto.getProfileImagePath() != null) jobSeeker.setProfileImagePath(dto.getProfileImagePath());
        if (dto.getResumePdfPath() != null) jobSeeker.setResumePdfPath(dto.getResumePdfPath());
        if (dto.getFullName() != null) jobSeeker.getUser().setFullName(dto.getFullName());
        if (dto.getUsername() != null) jobSeeker.getUser().setUsername(dto.getUsername());
        if (dto.getEmail() != null) jobSeeker.getUser().setEmail(dto.getEmail());
        if (dto.getPhoneNo() != null) jobSeeker.getUser().setPhoneNo(dto.getPhoneNo());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            String salt = PasswordUtils.generateSalt();
            String hashed = PasswordUtils.hashPassword(dto.getPassword(), salt);
            jobSeeker.getUser().setPasswordSalt(salt);
            jobSeeker.getUser().setPasswordHash(hashed);
        }
        JobSeekers updated = jobSeekerRepository.save(jobSeeker);
        return ResponseEntity.ok(JobSeekerMapper.toDto(updated));
    }
    //upload files
    @PostMapping("/upload/files")
    public ResponseEntity<JobSeekerResponse> uploadFiles(
            @RequestParam("id") Integer id,
            @RequestParam(value = "image", required = false) MultipartFile[] profileImages,
            @RequestParam(value = "resume", required = false) MultipartFile[] resumes
    ) throws IOException {

        JobSeekers jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jobseeker not found with id " + id));
//folder location
        String baseDir = "C:/JobPortalUploads/user_" + id + "/";
        File userDir = new File(baseDir);
        if (!userDir.exists()) userDir.mkdirs();

        if (profileImages != null) {
            for (MultipartFile file : profileImages) {
                if (!file.isEmpty()) {
                    String filePath = baseDir + "profile_" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    // store the last uploaded profile image path
                    jobSeeker.setProfileImagePath(filePath);
                }
            }
        }
        if (resumes != null) {
            for (MultipartFile file : resumes) {
                if (!file.isEmpty()) {
                    String filePath = baseDir + "resume_" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    // store the last uploaded resume path
                    jobSeeker.setResumePdfPath(filePath);
                }
            }
        }
        JobSeekers updated = jobSeekerRepository.save(jobSeeker);
        return ResponseEntity.ok(JobSeekerMapper.toDto(updated));
    }
}
