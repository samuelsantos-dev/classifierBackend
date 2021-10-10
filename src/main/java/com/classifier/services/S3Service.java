package com.classifier.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public List<String> findWithPrefix(String prefix) {
		List<String> fileNames = new ArrayList<>();

		try {
			ListObjectsV2Result result = s3client.listObjectsV2(bucketName, prefix);

			for (S3ObjectSummary summary : result.getObjectSummaries()) {
				String key = summary.getKey();
				if (!key.contains("profile")) {
					fileNames.add(key);
					LOG.info(key);
				}
			}
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}

		return fileNames;
	}

	public URI uploadFile(MultipartFile multipartFile, String path, String alternativeFileName) {
		try {
			String fileName = multipartFile.getOriginalFilename();

			if (path != null) {
				if (alternativeFileName != null)
					fileName = alternativeFileName;

				fileName = path + "/" + fileName;
			}

			InputStream inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();

			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, inputStream, meta);

			LOG.info("Upload finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (AmazonServiceException error) {
			LOG.info("AmazonServiceException: " + error.getErrorMessage());
			LOG.info("Status code: " + error.getErrorCode());
		} catch (AmazonClientException error) {
			LOG.info("AmazonClientException: " + error.getMessage());
		} catch (URISyntaxException error) {
			LOG.info(error.getMessage());
		}

		return null;
	}

	public void deleteFile(String fileName) {
		if (!s3client.doesObjectExist(bucketName, fileName))
			// throw new ObjectNotFoundException("Documento não encontrado! Nome: " +
			// fileName);

			s3client.deleteObject(bucketName, fileName);
	}

	public InputStream downloadFile(final String keyName) {
		LOG.info("Downloading an object with key= " + keyName);
		final S3Object s3Object = s3client.getObject(bucketName, keyName);
		return s3Object.getObjectContent();
	}

}
