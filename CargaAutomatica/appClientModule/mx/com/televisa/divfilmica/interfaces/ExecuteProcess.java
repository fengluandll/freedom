/**
* Project: CONS-0626
*
* File: ExecuteProcess.java
*
* Created on: Febrero 08, 2012 at 16:21
*
* Copyright (c) - Kaz Consulting / Argumel
*/

package mx.com.televisa.divfilmica.interfaces;

import java.io.IOException;

/**
 * Interface que al ser implementada obliga a usar los metodos
 *
 * @author Kaz Consulting / Argumel
 *
 * @version 1.0.0
 *
 * @date Febrero 08, 2012 at 16:21
 */
public interface ExecuteProcess {
    
        void executeERP();
        void executeFechasProgramacion();
        void executeBajas();
        boolean executeVencimientos();
        void executeBats() throws IOException, InterruptedException;
}
