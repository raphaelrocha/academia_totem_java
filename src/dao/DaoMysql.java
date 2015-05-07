/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import telas.Calendario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoMysql {
    final String DRIVER = "com.mysql.jdbc.Driver";
    private Calendario calendario = new Calendario();
    private String hostname;
    
    public DaoMysql(String host){
        this.hostname = host;
    }
    
    public String getHostName(){
        return hostname;
    }
    
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            String serverName = hostname;
            String mydatabase = "ACADEMIA_DB";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
            String username = "academia_user";  
            String password = "mysqlPAPS2015"; 
            //sua senha de acesso
            Connection connection = DriverManager.getConnection(url, username, password); 
            //System.out.println("Conectou");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro de conexao DB\n====================\n");
            //throw new RuntimeException(e);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro CLASSE");
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //return null;
    }
    public String getIdAcademiaAtivo(){
        String query = "SELECT CODIGO_CONFIG FROM ACADEMIA WHERE SITUACAO = 'ativo'";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        String ret = null;
        
        if(conn==null){
            return "erro";
        }
        
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
       
        try {
            // execute the query, and get a java resultset
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
        try {
            while (rs.next())
            {
                ret = rs.getString("CODIGO_CONFIG");
                // print the results
                System.out.format("idAcademiaAtivo: "+ret+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            return "erro";
        }
        return ret;
    }
    public String registraEntrada(String matricula){
        System.out.println("mat: "+matricula);
        String idAcademia = getIdAcademiaAtivo();
        
        if (idAcademia==null){
            System.out.println("id: "+idAcademia);
            return "0";
        }else if (idAcademia.equals("erro")){
            System.out.println("id: "+idAcademia);
            return "0";
        }
        String data = calendario.getData(1);
        String hora = calendario.getHora(1);
        String dia = calendario.getDia(1);
        
        String query = "SELECT REGISTRA_PRESENCA('"+matricula+"','"+dia+"','"+data+"','"+hora+"','"+idAcademia+"') as ret";
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        String ret = null;
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            // execute the query, and get a java resultset
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            //Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            System.out.format("reg: erro idacad\n====================\n");
            return "0";
            
        }
        try {
            while (rs.next())
            {
                ret = rs.getString("ret");
                // print the results
                System.out.format("reg: "+ret+"\n====================\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}

