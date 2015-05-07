/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Raphael
 * Classe responsável por coletar os diferentes parametros de configuração inseridos
 * no arquivo de configuração "config.ini"
 */
public class Arquivo{

    private final Calendario calendario;
    private final Properties config;
    private final String arquivo="config.ini";
    
    public Arquivo(Calendario calendario){
        //String path = this.getClass().getClassLoader().getResource("").getPath();
        this.config = new Properties();
        this.calendario = calendario;
        //String temp = path+"config.ini";
        //this.arquivo = temp.replace("/build/classes","");
        //String str = this.getClass().getClassLoader().getResource("").getPath();
        //System.out.println(str+arquivo);
    }
    /*
    Este método carrega somente os parametros para configuração da execução do 
    servidor.
    */
    public String getConfig(){
        String retorno=null;
        try{
            config.load(new FileInputStream(arquivo));
            retorno = config.getProperty("host");
            //retorno=config.getProperty("serverPort");
            //retorno=retorno+";"+config.getProperty("host");
            //retorno=retorno+";"+config.getProperty("comPort");
            //retorno=retorno+";"+config.getProperty("comPortSpeed");
            //retorno=retorno+";"+config.getProperty("useSwing");
        }catch (IOException ex) {
            System.err.println(calendario.getData(0)+" Falta arquivo de configuracao: "+arquivo);
        }
        return retorno;
    }
}