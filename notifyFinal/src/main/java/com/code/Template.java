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
	private String body1;
	
	@Column(length = Integer.MAX_VALUE)
	private String body2;
	
	@Column(length = Integer.MAX_VALUE)
	private String body3;
	
	@Column(length = Integer.MAX_VALUE)
	private String body4;

	public Template(String string, String string2) {
		setSubject(string);
		setBody(string2);
		// TODO Auto-generated constructor stub
	}

	public Template()
	{
		subject="defaultSubject";
		body1="defaultBody1";
		body2="defaultBody2";
		body3="defaultBody3";
		body4="defaultBody4";
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body1+body2+body3+body4;
	}

	public void setBody(String input) 
	{
		int length = input.length();
        int first = length/4;
        int second = first*2;
        int third = first*3;
        body1 = input.substring(0, first);
        body2 = input.substring(first, second);
        body3 = input.substring(second, third);
        body4 = input.substring(third, length);
	}

}
