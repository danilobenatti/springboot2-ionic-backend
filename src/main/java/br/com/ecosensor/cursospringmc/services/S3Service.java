package br.com.ecosensor.cursospringmc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.ecosensor.cursospringmc.services.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String originalFilename = multipartFile.getOriginalFilename();
			InputStream inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(inputStream, originalFilename, contentType);
		} catch (IOException ex) {
			throw new FileException("Error of IO: " + ex.getMessage());
		}
	}
	
	public URI uploadFile(InputStream inputStream, String filename,
			String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			log.info("Starting upload!");
			s3Client.putObject(bucketName, filename, inputStream, metadata);
			log.info("Upload finished!");
			return s3Client.getUrl(bucketName, filename).toURI();
		} catch (URISyntaxException ex) {
			throw new FileException(
					"Error converting URL to URI: " + ex.getMessage());
		}
		
	}
	
}
