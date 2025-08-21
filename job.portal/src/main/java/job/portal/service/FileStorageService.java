package job.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("uploads");
    public FileStorageService() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!", e);
        }
    }

    public String storeFile(MultipartFile file, String prefix, Long userId) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }
        String filename = prefix + "_" + userId + "_" + file.getOriginalFilename();
        Path destinationFile = this.rootLocation.resolve(Paths.get(filename))
                .normalize().toAbsolutePath();
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return destinationFile.toString();
    }
}
