/**
* Project: CONS-0626
*
* File: InitProcess.java
*
* Created on: Febrero 05, 2012 at 11:03
*
* Copyright (c) - Kaz Consulting / Argumel
*/
package mx.com.televisa;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Rectangle;

import java.awt.Toolkit;

import java.util.Date;
import java.util.Timer;

import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import mx.com.televisa.divfilmica.automatic.HypDivExecuteProcess;

/**
 * Clase inicial que ejecuta la carga Automatica
 *
 * @author Kaz Consulting / Argumel
 *
 * @version 1.0.0
 *
 * @date Febrero 05, 2012 at 11:03
 */
public class InitProcess extends JFrame {
    private JProgressBar jbrProcess = new JProgressBar();
    private JLabel lblTitle = new JLabel();

    public InitProcess() {
        try {
               jbInit();
               Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
               int pantalla_ancho=(int)d.getWidth();
               int forma_ancho=(int) this.getWidth();
               int pantalla_alto=(int)d.getHeight();
               int forma_alto=(int) this.getHeight();
               setLocation((pantalla_ancho - forma_ancho) / 2,(pantalla_alto - forma_alto) / 2+90);
    
               final Timer timer = timer();
               final SwingWorker worker = new SwingWorker(){
               @Override
               protected Object doInBackground() {
                   try{
                       //System.out.println("PASO1");
                       //JOptionPane.showMessageDialog(null,"Paso1","INFO",JOptionPane.INFORMATION_MESSAGE);
                        HypDivExecuteProcess h = new HypDivExecuteProcess();
                        System.exit(0);

                   }catch(Exception e){
                       System.out.println("2");
                        JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                   }
                   return null;
               }

                   };

                   worker.execute();
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private Timer timer(){
           TimerTask timerTask = new TimerTask()
               {
                    public void run()
                    {
                        new Thread(new thread1()).start();
                    }
               };

           final Timer timer = new Timer();
           timer.schedule(timerTask, new Date(), 1600);
           return timer;
       }
    
    public class thread1 implements Runnable{

            public void run(){

                for (int i=0; i<=100; i++){ //Progressively increment variable i

                    jbrProcess.setValue(i); //Set value
                    jbrProcess.setStringPainted(true);
                    jbrProcess.setString("Ejecutando");

                    try{Thread.sleep(10);} //Sleep 50 milliseconds

                    catch (InterruptedException err){
                        System.out.println("3");
                        }

                }

            }
         }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(400, 110));
        this.setDefaultCloseOperation(3);
        jbrProcess.setBounds(new Rectangle(95, 45, 210, 20));
        lblTitle.setText("Carga Automatica Iniciada");
        lblTitle.setBounds(new Rectangle(80, 15, 260, 20));
        lblTitle.setBackground(new Color(0, 74, 231));
        lblTitle.setForeground(new Color(0, 74, 231));
        lblTitle.setFont(new Font("Tahoma", 0, 20));
        this.getContentPane().add(lblTitle, null);
        this.getContentPane().add(jbrProcess, null);
    }
    
    public static void main(String[] args) {
        new InitProcess().setVisible(true);
    }
}
