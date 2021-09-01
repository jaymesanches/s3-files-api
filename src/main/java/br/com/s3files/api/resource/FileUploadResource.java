package br.com.s3files.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.s3files.api.service.S3;

@RestController
@RequestMapping("/files")
public class FileUploadResource {
	
	@Autowired
	private S3 s3;
	
	@PostMapping
	public void upload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		s3.salvar(file);
	}

	@GetMapping
	public List<String> list() {
		return s3.listarBuckets();
	}
}
