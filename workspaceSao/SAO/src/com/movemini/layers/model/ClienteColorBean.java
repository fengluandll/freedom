package com.movemini.layers.model;

import java.io.Serializable;

import org.apache.poi.ss.usermodel.IndexedColors;

public class ClienteColorBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int 			nVeces;
	private short 	color;
	
	
	public int getnVeces() {
		return nVeces;
	}
	public void setnVeces(int nVeces) {
		this.nVeces = nVeces;
	}
	public short getColor() {
		return color;
	}
	public void setColor(short color) {
		this.color = color;
	}
	
	

}
