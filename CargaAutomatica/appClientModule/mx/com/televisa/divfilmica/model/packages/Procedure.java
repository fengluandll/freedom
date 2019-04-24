package mx.com.televisa.divfilmica.model.packages;

/**
* Project: CONS-0626
*
* File: Procedure.java
*
* Created on: Febrero 2, 2012 at 12:00
*
* Copyright (c) - Kaz Consulting 
*/


/**
* Clase que representa un SP durante su llamado desde Java
*
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Febrero 07, 2012, 8:23 pm
*/
public class Procedure {

    private String name;
    private int paramCount;

    
    /**
     *Método constructor que inicializa los atributos de clase
     */
    public Procedure(String tsName, int tiParamCount) {
        this.name = tsName;
        this.paramCount = tiParamCount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParamCount() {
        return paramCount;
    }

    public void setParamCount(int paramCount) {
        this.paramCount = paramCount;
    }
}


