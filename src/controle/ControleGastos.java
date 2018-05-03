/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloGastos;


public class ControleGastos {
    
    ConexaoBD conex = new ConexaoBD();
    ModeloGastos gast = new ModeloGastos();
    
    public void Salvar(ModeloGastos gast){
        
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into adicionargastos(valor_adicionargastos,data_adicionargastos) values(?,?)");
            pst.setFloat(1, gast.getValor());
            pst.setDate(2, gast.getData());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Gasto inserido com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o gasto\nErro:"+ex); 
        }
        
        conex.desconecta();
        
    }
 
   public void Excluir(ModeloGastos gast) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionargastos where cod_adicionargastos = ?");
            pst.setInt(1, gast.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Gasto excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o gasto");
        }
        
    }
   public void Editar (ModeloGastos gast){
        
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionargastos where cod_adicionargastos = ?");
            pst.setInt(1, gast.getCodigo());
            pst.execute();
            PreparedStatement gst = conex.con.prepareStatement("insert into adicionargastos(cod_adicionargastos,valor_adicionargastos,data_adicionargastos) values(?,?,?)");
            gst.setInt(1, gast.getCodigo());
            gst.setFloat(2, gast.getValor());
            gst.setDate(3, gast.getData());
            gst.execute();
            
            JOptionPane.showMessageDialog(null, "Gasto modificado com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao modificar o gasto:"+ex); 
        }
        
        conex.desconecta();
        
    }
   
}

