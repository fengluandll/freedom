package com.movemini.data;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

//import javafx.scene.web.WebHistory;

public class ConnectionCounter {

	public static int openConnections = 0;
	
	public static void newConnection() {
		
		openConnections++;
	}

	public static void closeConnection() {
		
		openConnections--;
	}

	public static int count() {
		
		return openConnections;
	}

}
