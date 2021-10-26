package com.classifier.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.classifier.domain.Document;
import com.classifier.domain.User;
import com.classifier.enums.TypeDocument;
import com.classifier.services.DocumentService;

@RestController
@RequestMapping(value = "/documents")
public class DocumentResource {
	
	@Autowired
	private DocumentService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Document> uploadDocument(@RequestParam("type") Integer type,
			@RequestPart(name = "description", required = false) String descricao,
			@RequestPart("file") MultipartFile document) {
		User user = new User();
		user.setCpf("45662241805");
		service.uploadDocument(user, document, TypeDocument.CPF, "description");
		return ResponseEntity.ok(null);
	}
}
