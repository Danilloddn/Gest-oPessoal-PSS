/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoBD {
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/PSS";
    private String usuario = "postgres";
    private String senha = "uempss";
    public Connection con;
    
    public void conexao(){ //método responsavel por realizar a conexao com a base de dados
        System.setProperty("jdbc.Drivers", driver);
        try {
            con = DriverManager.getConnection(caminho,usuario,senha);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro de conexao:\n"+ex.getMessage());
            
        }
    }
    
    public void executaSql (String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro executarSQL:\n"+ex.getMessage());
        }

    }
    
    public void desconecta(){ //método responsavel que desconecta o banco de dados
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexao com o BD:\n"+ex.getMessage());
        }
    }
    
    
}
