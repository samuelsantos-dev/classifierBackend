package com.classifier.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.classifier.domain.Document;

@RestController
@RequestMapping(value = "/documents")
public class DocumentResource {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Document> uploadDocument(@RequestParam("type") Integer type,
			@RequestPart(name = "description", required = false) String descricao,
			@RequestPart("file") MultipartFile documento) {
		return ResponseEntity.ok(null);
	}
}
