package br.com.s3files.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface Armazenamento {
	void salvar(MultipartFile file);
}
