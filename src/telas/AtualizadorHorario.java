/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author rli
 */
public class AtualizadorHorario extends Thread {  
  
    private final JLabel hr;  
    private boolean mostrarData;
    private final Calendario calendario = new Calendario();
  
    public AtualizadorHorario(JLabel hora) {  
        this.hr = hora;  
    }  
  
    public void mostrarData(boolean mostrar) {  
        if (mostrar) {  
            this.mostrarData = true;  
        } else {  
            this.mostrarData = false;  
        }  
    }  
  
    @Override  
    public void run() {  
        try {  
            while (true) {
                String dia = calendario.getDiaFormatado(0);
                Date d = new Date();  
                StringBuffer data = new StringBuffer();  
                if (mostrarData) {  
                    SimpleDateFormat sdfData = new SimpleDateFormat("dd.MM.yyyy");  
                    data.append(sdfData.format(d));  
                    data.append(" - ");  
                }  
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
                this.hr.setText(dia + " - "+ data.toString() + sdf.format(d));  
                Thread.sleep(1000);  
                this.hr.revalidate();  
            }  
        } catch (InterruptedException ex) {  
            System.out.println("Problema na atualização da data/hora");  
            ex.printStackTrace();  
        }  
    }  
}  
