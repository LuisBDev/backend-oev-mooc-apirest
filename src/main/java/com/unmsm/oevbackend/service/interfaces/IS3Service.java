package com.unmsm.oevbackend.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public interface IS3Service {

    String createBucket(String bucketName);

    Boolean checkIfBucketExists(String bucketName);

    List<String> getAllBuckets();

    void deleteFile(String bucketName, String key);

    Boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException;

    String generatePreSignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePreSignedDownloadUrl(String bucketName, String key, Duration duration);


}
