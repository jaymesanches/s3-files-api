package br.com.s3files.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.s3files.api.service.FileService;

@RestController
@RequestMapping("/files")
public class FileUploadResource {
	
	private FileService fileService;
	
	@Autowired
	public FileUploadResource(FileService fileService) {
		this.fileService = fileService;
	}
	
	@PostMapping
	public void upload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		this.fileService.salvar(file);
	}

	@GetMapping
	public List<String> list() {
		return this.fileService.listarBuckets();
	}
}
