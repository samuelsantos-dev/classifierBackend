package com.classifier.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.classifier.domain.Document;
import com.classifier.domain.User;
import com.classifier.dto.RequestClassifierDTOO;
import com.classifier.dto.ResponseClassifier;
import com.classifier.repositories.DocumentRepository;
import com.classifier.services.exception.DataIntegrityException;
import com.classifier.services.exception.FileException;

@Service
public class DocumentService {

	@Autowired
	private S3Service service;
	
	@Autowired
	private APIPythonService pythonService;

	@Autowired
	private DocumentRepository rep;

	private static final String USER_FOLDER_NAME = "user";
	private static final String USER_PREFIX = "user";
	private static final String[] validExtensions = { "pdf", "jpg", "png", "jpeg" };

	public List<String> findFileNames(User user) {
		String prefix = USER_FOLDER_NAME + "/" + USER_PREFIX + "-" + user.getCpf().replaceAll("[^\\d]", "");
		return service.findWithPrefix(prefix);
	}

	public Document uploadDocument(User user, MultipartFile document, String description) {
		try {
			if (description != null)
				description = URLDecoder.decode(description, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new DataIntegrityException("Formato de descrição inválido");
		}

		if (!FilenameUtils.isExtension(document.getOriginalFilename().toLowerCase(), validExtensions))
			throw new FileException("Extensão de arquivo fora dos tipos permitidos ("
					+ String.join(", ", validExtensions).toUpperCase() + ")");

		String filePrefix = USER_PREFIX + "-" + user.getCpf().replaceAll("[^\\d]", "");

		String originalFilename = document.getOriginalFilename();

		String extension = document.getOriginalFilename().substring(originalFilename.lastIndexOf("."));

		String name = filePrefix + "-" + extension;
		URI link = service.uploadFile(document, USER_FOLDER_NAME, name);
		
		String s = null;
		try {
			s = new String(Base64.getEncoder().encode(document.getInputStream().readAllBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ResponseClassifier response =  pythonService.classifierImage(new RequestClassifierDTOO(s));
		if (!response.isDocument())
			throw new DataIntegrityException("Imagem inválida, essa imagem não é um documento");

		Document documentUser = new Document(null, name, response.getType(), link.toString(), extension, document.getSize(),
				null);

		return rep.save(documentUser);
	}
	
	
	public List<Document> getDocuments(){
		return rep.findAll();
	}

}
