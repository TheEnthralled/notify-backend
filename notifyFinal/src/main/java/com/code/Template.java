package com.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "templates")
public class Template {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int template_id;
	
	@Column(length = Integer.MAX_VALUE)
	private String subject;
	
	@Column(length = Integer.MAX_VALUE)
	private String body;

	public Template(String string, String string2) {
		setSubject(string);
		setBody(string2);
		// TODO Auto-generated constructor stub
	}

	public Template()
	{
		subject="defaultSubject";
		body="defaultBody";
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
