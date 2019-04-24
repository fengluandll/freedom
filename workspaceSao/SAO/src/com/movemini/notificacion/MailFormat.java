package com.movemini.notificacion;

public class MailFormat {
	private String Subject;
	private String content;
	private boolean isHtmlContent;
	
	public MailFormat(){}
	
	public MailFormat(String subject, String content, boolean isHtmlContent) {
		super();
		Subject = subject;
		this.content = content;
		this.isHtmlContent = isHtmlContent;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isHtmlContent() {
		return isHtmlContent;
	}
	public void setHtmlContent(boolean isHtmlContent) {
		this.isHtmlContent = isHtmlContent;
	}
}
