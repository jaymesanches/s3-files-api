package br.com.s3files.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

@Component
public class S3 {
	@Autowired
	private AmazonS3 s3;

	public void salvar(MultipartFile multiPartFile) {
		var bucket = "s3-files-testes";

		var file = new File(multiPartFile.getName());
		FileOutputStream os = null;

		try {
			os = new FileOutputStream(file);
			os.write(multiPartFile.getBytes());
			s3.putObject(bucket, "teste.txt", file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> listarBuckets() {
		System.out.println("Listando buckets...");
		var listBuckets = s3.listBuckets();

		return listBuckets.stream().map(b -> b.getName()).collect(Collectors.toList());

	}
}
