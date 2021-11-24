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
import com.classifier.enums.TypeDocument;
import com.classifier.repositories.DocumentRepository;
import com.classifier.services.exception.DataIntegrityException;
import com.classifier.services.exception.FileException;

@Service
public class DBService {
	
	@Autowired
	private DocumentRepository rep;

	
	public void initDtbase() {
		Document doc = new Document(null, "teste", TypeDocument.CPF.getCod(), "teste", ".jpg", 0L, null);
		rep.save(doc);
	}
	
	
}
