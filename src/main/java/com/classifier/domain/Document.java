package com.classifier.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.classifier.enums.TypeDocument;

@Entity
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer type;
	private String url;
	private String extension;
	private long size_bytes;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TypeDocument getType() {
		return TypeDocument.toEnum(type);
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public long getSize_bytes() {
		return size_bytes;
	}

	public void setSize_bytes(long size_bytes) {
		this.size_bytes = size_bytes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document(Integer id, String name, Integer type, String url, String extension, long size_bytes, User user) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.extension = extension;
		this.size_bytes = size_bytes;
		this.user = user;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
