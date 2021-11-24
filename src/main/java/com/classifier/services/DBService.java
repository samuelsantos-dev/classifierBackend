package com.classifier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classifier.domain.Document;
import com.classifier.enums.TypeDocument;
import com.classifier.repositories.DocumentRepository;

@Service
public class DBService {
	
	@Autowired
	private DocumentRepository rep;

	
	public void initDtbase() {
		Document doc = new Document(null, "teste", TypeDocument.CPF.getCod(), "teste", ".jpg", 0L, null);
		rep.save(doc);
	}
	
	
}
