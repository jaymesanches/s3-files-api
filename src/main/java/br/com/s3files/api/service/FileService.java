package br.com.s3files.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public void salvar(MultipartFile file) {
		var s3 = new S3();
		s3.salvar(file);
	}
	
	public List<String> listarBuckets(){
		var s3 = new S3();
		return s3.listarBuckets();
	}
}
