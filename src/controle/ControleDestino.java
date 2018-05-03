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
import modelo.ModeloDestino;

public class ControleDestino {
    ConexaoBD conex = new ConexaoBD();
    ModeloDestino dest = new ModeloDestino();
    
    
    public void Salvar(ModeloDestino dest){

        conex.conexao();
        try {
            
            PreparedStatement pst = conex.con.prepareStatement("insert into adicionardestino(nome_adicionardestino,data_adicionardestino) values(?,?)");
            pst.setString(1, dest.getNome());
            pst.setDate(2, dest.getData());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados!\nErro:"+ex); 
        }
      
        conex.desconecta();
        
    }
   public void Excluir(ModeloDestino rec) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionardestino where cod_adicionardestino = ?");
            pst.setInt(1, rec.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dado excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o dado");
        }
        
    }
   public void Editar (ModeloDestino dest){
        
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionardestino where cod_adicionardestino = ?");
            pst.setInt(1, dest.getCodigo());
            pst.execute();
            PreparedStatement gst = conex.con.prepareStatement("insert into adicionardestino(cod_adicionardestino,nome_adicionardestino,data_adicionardestino) values(?,?,?)");
            gst.setInt(1, dest.getCodigo());
            gst.setString(2, dest.getNome());
            gst.setDate(3, dest.getData());
            gst.execute();
            
            JOptionPane.showMessageDialog(null, "Destino modificado com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao modificar o destino:"+ex); 
        }
        
        conex.desconecta();
        
    }
}
