package br.com.s3files.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3 implements Armazenamento {
	
	@Value("${aws.accessKey}")
	private String accessKey;
	
	@Value("${aws.secretKey}")
	private String secretKey;

	@Override
	public void salvar(MultipartFile multiPartFile) {
		var s3 = s3Connect();
		var bucket = "s3-files-testes";
		
		var file = new File(multiPartFile.getName());

		try {
			var os = new FileOutputStream(file);
			os.write(multiPartFile.getBytes());
			s3.putObject(bucket, "teste.txt", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> listarBuckets() {
		var s3 = s3Connect();
		System.out.println("Listando buckets...");
		var listBuckets = s3.listBuckets();

		return listBuckets.stream().map(b -> b.getName()).collect(Collectors.toList());

	}

	private AmazonS3 s3Connect() {
		if(accessKey == null || secretKey == null) {
			throw new RuntimeException("Credenciais vazias");
		}

		var awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.SA_EAST_1).build();

	}

}
