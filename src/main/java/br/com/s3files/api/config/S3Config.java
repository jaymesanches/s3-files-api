package br.com.s3files.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import br.com.s3files.api.config.property.S3FilesProperty;

@Configuration
public class S3Config {

	@Autowired
	private S3FilesProperty property;

	@Bean
	public AmazonS3 amazonS3() {
		var accessKey = property.getS3().getAccessKey();
		var secretKey = property.getS3().getSecretKey();

		if (accessKey == null || secretKey == null) {
			throw new RuntimeException("Credenciais vazias");
		}

		var awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.SA_EAST_1).build();
	}

}
