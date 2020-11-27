package by.ttre16.briana.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final AmazonS3 amazonS3Client;
    private static final Logger log = getLogger(ImageService.class);

    @Value("${aws.bucketName}")
    private String bucketName;

    @SneakyThrows
    public String upload(MultipartFile multipartFile, String key) {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(requireNonNull(fileName));
        multipartFile.transferTo(file);
        String path = key + "/" + fileName;
        amazonS3Client.putObject(bucketName, path, file);
        Files.delete(file.toPath());
        return path;
    }

    @SneakyThrows
    public byte[] download(String path) {
        return amazonS3Client
                .getObject(bucketName, path)
                .getObjectContent()
                .readAllBytes();
    }

    public void delete(String path) {
        amazonS3Client.deleteObject(bucketName, path);
    }
}
