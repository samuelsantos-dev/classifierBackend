package com.classifier.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.classifier.domain.Document;

public interface DocumentRepository extends JpaRepositoryImplementation<Document, Integer> {

}
