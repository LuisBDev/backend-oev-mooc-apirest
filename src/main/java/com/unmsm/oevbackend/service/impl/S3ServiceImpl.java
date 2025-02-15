package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.service.interfaces.IS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements IS3Service {

    private final S3Client s3Client;

    @Override
    public String createBucket(String bucketName) {
        return this.s3Client.createBucket(bucketBuilder -> bucketBuilder.bucket(bucketName)).location();
    }

    @Override
    public Boolean checkIfBucketExists(String bucketName) {
        try {
            this.s3Client.headBucket(headBucketRequest -> headBucketRequest.bucket(bucketName));
            return true;
        } catch (S3Exception exception) {
            return false;
        }
    }

    @Override
    public List<String> getAllBuckets() {
        ListBucketsResponse listBucketsResponse = this.s3Client.listBuckets();
        if (listBucketsResponse.hasBuckets()) {
            return listBucketsResponse.buckets().stream().map(Bucket::name).toList();
        }
        return List.of();
    }

    @Override
    public void deleteFile(String bucketName, String key) {
        this.s3Client.deleteObject(deleteObjectRequest -> deleteObjectRequest.bucket(bucketName).key(key));
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        PutObjectResponse putObjectResponse = this.s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        return putObjectResponse.sdkHttpResponse().isSuccessful();
    }

    @Override
    public String generatePreSignedUploadUrl(String bucketName, String key, Duration duration) {
        try (S3Presigner presigner = S3Presigner.create()) { // Try-With-Resources para cerrar automáticamente
            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(duration)
                    .putObjectRequest(putObjectRequest -> putObjectRequest.bucket(bucketName).key(key))
                    .build();

            return presigner.presignPutObject(presignRequest).url().toString();
        }
    }


    @Override
    public String generatePreSignedDownloadUrl(String bucketName, String key, Duration duration) {
        try (S3Presigner presigner = S3Presigner.create()) { // Try-With-Resources para cerrar automáticamente

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(duration)
                    .getObjectRequest(getObjectRequest -> getObjectRequest.bucket(bucketName).key(key))
                    .build();

            return presigner.presignGetObject(presignRequest).url().toString();
        }
    }
}
