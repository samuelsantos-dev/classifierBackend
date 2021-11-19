package com.classifier.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.classifier.domain.Document;
import com.classifier.domain.User;
import com.classifier.enums.TypeDocument;
import com.classifier.services.DocumentService;
import com.classifier.services.UserService;

@RestController
@RequestMapping(value = "/documents")
public class DocumentResource {
	
	@Autowired
	private DocumentService service;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Document> uploadDocument(@PathVariable("id") Integer id,
			@RequestPart(name = "description", required = false) String descricao,
			@RequestPart("file") MultipartFile document) {
		User user = userService.find(id);
		Document obj = service.uploadDocument(user, document, TypeDocument.CPF, "description");
		return ResponseEntity.ok(obj);
	}
}