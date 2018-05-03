/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ModeloReceita;

public class ControleReceita {
    ConexaoBD conex = new ConexaoBD();
    ModeloReceita rec = new ModeloReceita();
    
    
    public void Salvar(ModeloReceita rec){

        conex.conexao();
        try {
            
            PreparedStatement pst = conex.con.prepareStatement("insert into adicionarreceita(valor_adicionarreceita,data_adicionarreceita) values(?,?)");
            pst.setFloat(1, rec.getValor());
            pst.setDate(2, rec.getData());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Receita inserida com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a receita\nErro:"+ex); 
        }
        
        conex.desconecta();
        
    }
       public void Excluir(ModeloReceita rec) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionarreceita where cod_adicionarreceita = ?");
            pst.setInt(1, rec.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Receita excluida com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a receita");
        }
    }

   public void Editar (ModeloReceita rec){
        
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from adicionarreceita where cod_adicionarreceita = ?");
            pst.setInt(1, rec.getCodigo());
            pst.execute();
            PreparedStatement gst = conex.con.prepareStatement("insert into adicionarreceita(cod_adicionarreceita,valor_adicionarreceita,data_adicionarreceita) values(?,?,?)");
            gst.setInt(1, rec.getCodigo());
            gst.setFloat(2, rec.getValor());
            gst.setDate(3, rec.getData());
            gst.execute();
            JOptionPane.showMessageDialog(null, "Receita modificada com sucesso"); 
            
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao modificar o dado:"+ex); 
        }
        
        conex.desconecta();
        
    }
}
