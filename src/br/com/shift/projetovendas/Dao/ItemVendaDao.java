/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;

import br.com.shift.projetovendas.jdbc.ConnectionFactory;
import br.com.shift.projetovendas.Model.ItemVenda;
import br.com.shift.projetovendas.Model.Produtos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author robsonPaiva
 */
public class ItemVendaDao {
    
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    
    //Contrutor padrão para conectar automaticamento com o banco de dados
    public ItemVendaDao(){
        this.con = new ConnectionFactory().getConnection();
    }
    
     //Método que cadastrar Itens
    public void cadastraItem(ItemVenda obj){
        try {
            String sql = "insert into tb_itensvendas(venda_id, produto_id,qtd,subtotal) values(?,?,?,?)";
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, obj.getVenda().getId());
            pstm.setInt(2, obj.getProduto().getId());
            pstm.setInt(3, obj.getQtd());
            pstm.setDouble(4, obj.getSubotal());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
    //Metodo que lista Itens de uma venda por id
    
    public List<ItemVenda> listaItensPorVenda(int venda_id){
        List<ItemVenda>lista = new ArrayList<>();
        
        try {
            String sql = "select i.id, p.descricao, i.qtd,p.cor, p.tamanho, p.preco, i.subtotal "
                    + " from tb_itensvendas as i "
                    + " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";
                    
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, venda_id);
            
            rst = pstm.executeQuery();
            while(rst.next()){
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
                
                
                
                
                prod.setDescricao(rst.getString("p.descricao"));
                prod.setPreco(rst.getDouble("p.preco"));
                prod.setCor(rst.getString("p.cor"));
                prod.setTamanho(rst.getString("p.tamanho"));
                item.setId(rst.getInt("i.id"));
                item.setQtd(rst.getInt("i.qtd"));
                item.setSubotal(rst.getDouble("i.subtotal"));
                item.setProduto(prod);
                
                lista.add(item);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
        return lista;
    }
    
}
