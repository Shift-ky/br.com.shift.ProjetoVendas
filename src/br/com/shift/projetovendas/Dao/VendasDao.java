/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;

import br.com.shift.projetovendas.Model.Clientes;
import br.com.shift.projetovendas.Model.Funcionarios;
import br.com.shift.projetovendas.Model.Vendas;
import br.com.shift.projetovendas.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author robsonPaiva
 */
public class VendasDao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    private ArrayList<Vendas> listaId;
    private ArrayList<Vendas> listaVendas;
    
    //Contrutor padrão para conectar automaticamento com o banco de dados
    public VendasDao() {
        this.listaId = new ArrayList<>();
        this.listaVendas = new ArrayList<>();

        this.con = new ConnectionFactory().getConnection();
    }

    //Cadastrar a venda
    public void cadastraVenda(Vendas obj) {

        try {
            String sql = "insert into tb_vendas(cliente_id,funcionario_id, data_venda, total_venda, observacoes) values(?,?,?,?,?)";

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, obj.getCliente().getId());
            pstm.setInt(2, obj.getFuncionario().getId());
            pstm.setString(3, obj.getData_venda());
            pstm.setDouble(4, obj.getTotal_venda());
            pstm.setString(5, obj.getObs());
            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    //Retorna a ultima venda
    public int retornaUltimaVenda() {
        int idVenda = 0;
        try {
            String sql = "select id from tb_vendas";
            pstm = con.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()) {
                Vendas p = new Vendas();
                p.setId(rst.getInt("id"));

                listaId.add(p);
            }
            if (listaId.size() > 0) {
                idVenda = listaId.get(listaId.size() - 1).getId();
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro em retorna o ultimo id" + erro);
        }
        return idVenda;
    }

    //Metodo que filtra vendas por data
    public List listaVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            List<Vendas> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select v.id,date_format(v.data_venda,'%d/%m/%y') as data_formatada, c.nome, v.total_venda, v.observacoes "
                    + " from tb_vendas as v "
                    + " inner join tb_clientes as c on(v.cliente_id = c.id) "
                    + " where v.data_venda between ? and ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, dataInicio.toString());
            pstm.setString(2, dataFim.toString());

            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rst.getInt("v.id"));
                obj.setData_venda(rst.getString("data_formatada"));
                c.setNome(rst.getString("c.nome"));
                obj.setTotal_venda(rst.getDouble("v.total_venda"));
                obj.setObs(rst.getString("v.observacoes"));
                obj.setCliente(c);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }

    public double retornaTotalVendaPorData(LocalDate data_venda) {
        try {
            double totalvenda = 0;

            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, data_venda.toString());

            rst = pstm.executeQuery();

            if (rst.next()) {
                totalvenda = rst.getDouble("total");
            }
            return totalvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Vendas retornaVendasPorFuncioanrio(int id) {
        String sql = " SELECT sum(v.total_venda) as total, f.nome, f.cpf from tb_vendas as v inner join tb_funcionarios as f on(funcionario_id = f.id) where f.id = ? ";
        Vendas vendas = new Vendas();
        Funcionarios fun = new Funcionarios();
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                
                fun.setNome(rst.getString("f.nome"));
                fun.setCpf(rst.getString("f.cpf"));
                
                vendas.setTotal_venda(rst.getDouble("total"));
                vendas.setFuncionario(fun);
            }

        } catch (SQLException e) {
        }
        return vendas;
    }
    
    public ArrayList<Vendas> listaDeVendasPorId(int id){
        String sql = "select v.id, c.nome, date_format(data_venda,'%d/%m/%y') as data_formatada, f.nome, v.observacoes, "
                   + "v.total_venda from tb_vendas as v "
                   + "INNER join tb_funcionarios as f ON(funcionario_id = f.id) "
                   + "INNER join tb_clientes as c on(cliente_id = c.id) "
                   + "where f.id = ?";
        
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            rst = pstm.executeQuery();
            
            while(rst.next()){
                Vendas vendas = new Vendas();
                Clientes clientes = new Clientes();
                Funcionarios funcionarios = new Funcionarios();
                
                clientes.setNome(rst.getString("c.nome"));
                funcionarios.setNome(rst.getString("f.nome"));
                
                vendas.setCliente(clientes);
                vendas.setId(rst.getInt("v.id"));
                vendas.setData_venda(rst.getString("data_formatada"));
                vendas.setFuncionario(funcionarios);
                vendas.setObs(rst.getString("v.observacoes"));
                vendas.setTotal_venda(rst.getDouble("v.total_venda"));
                
                listaVendas.add(vendas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro em listar as vendas dos funcionarios" + e);
        }
        
        return listaVendas;
    }

}
