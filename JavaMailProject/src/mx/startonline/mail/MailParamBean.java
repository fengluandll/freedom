/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.startonline.mail;

import java.io.Serializable;

/**
 * @author Argumedo
 *
 */
public class MailParamBean implements Serializable{


private static final long serialVersionUID = 1L;

private String to;
private String from;
private String userName;
private String password;
private String nomCorreoAparecer;
private String subJect;
private String contenidoHtml;




public String getContenidoHtml() {
	return contenidoHtml;
}




public void setContenidoHtml(String contenidoHtml) {
	this.contenidoHtml = contenidoHtml;
}




public String getTo() {
	return to;
}




public void setTo(String to) {
	this.to = to;
}




public String getFrom() {
	return from;
}




public void setFrom(String from) {
	this.from = from;
}




public String getUserName() {
	return userName;
}




public void setUserName(String userName) {
	this.userName = userName;
}




public String getPassword() {
	return password;
}




public void setPassword(String password) {
	this.password = password;
}




public String getNomCorreoAparecer() {
	return nomCorreoAparecer;
}




public void setNomCorreoAparecer(String nomCorreoAparecer) {
	this.nomCorreoAparecer = nomCorreoAparecer;
}




public String getSubJect() {
	return subJect;
}




public void setSubJect(String subJect) {
	this.subJect = subJect;
}




public MailParamBean() {
	
}


}
