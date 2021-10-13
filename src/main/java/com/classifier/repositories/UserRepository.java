package com.classifier.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.classifier.domain.User;

public interface UserRepository extends JpaRepositoryImplementation<User, Integer> {

}
