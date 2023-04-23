/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;
import br.com.shift.projetovendas.Model.Fornecedores;
import br.com.shift.projetovendas.Model.Produtos;
import br.com.shift.projetovendas.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author robsonPaiva
 */
public class ProdutosDao {
    
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    
     //Contrutor padrão para conectar automaticamento com o banco de dados
    public ProdutosDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo cadastra Cliente
    public void cadastraProduto(Produtos obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "INSERT into tb_produtos(descricao,cor,tamanho,preco,qtd_estoque,for_id) "
                    + "values(?,?,?,?,?,?)";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getDescricao());
            pstm.setString(2, obj.getCor());
            pstm.setString(3, obj.getTamanho());
            pstm.setDouble(4, obj.getPreco());
            pstm.setInt(5, obj.getQtd_estoque());
            pstm.setInt(6, obj.getFornecedores().getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //Metodo editar Produtos 
    public void alterarProduto(Produtos obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "update tb_produtos set descricao=?, cor=?,tamanho=?, preco =?, qtd_estoque=?, for_id=? where id = ?";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getDescricao());
            pstm.setString(2, obj.getCor());
            pstm.setString(3, obj.getTamanho());
            pstm.setDouble(4, obj.getPreco());
            pstm.setInt(5, obj.getQtd_estoque());
            pstm.setInt(6, obj.getFornecedores().getId());

            pstm.setInt(7, obj.getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    //Metodo excluir Cliente
    public void excluirProdutos(Produtos obj) {
        try {

            //Comando SQL para deletar um registro
            String sql = "delete from tb_produtos where id = ?";
            //Conectar com o banco de dados e setar os dados para realizar o comando deletar
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, obj.getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
        }

    }

    //Metodo listar clientes
    public List listaProdutos() {
        try {
            List<Produtos> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select p.id, p.descricao, p.cor, p.tamanho, p.preco, p.qtd_estoque, f.nome "
                    + "from bdvendas.tb_produtos as p "
                    + "inner join bdvendas.tb_fornecedores as f on (p.for_id = f.id)";

            pstm = con.prepareStatement(sql);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rst.getInt("p.id"));
                obj.setDescricao(rst.getString("p.descricao"));
                obj.setCor(rst.getString("p.cor"));
                obj.setTamanho(rst.getString("p.tamanho"));
                obj.setPreco(rst.getDouble("p.preco"));
                obj.setQtd_estoque(rst.getInt("p.qtd_estoque"));
                f.setNome(rst.getString("f.nome"));
                obj.setFornecedores(f);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }

    public List buscaProdutoPeloNome(String nome) {
        try {
            List<Produtos> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select p.id, p.descricao, p.cor, p.tamanho, p.preco, p.qtd_estoque, f.nome "
                    + "from bdvendas.tb_produtos as p "
                    + "inner join bdvendas.tb_fornecedores as f on (p.for_id = f.id) "
                    + "where descricao like ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rst.getInt("p.id"));
                obj.setDescricao(rst.getString("p.descricao"));
                obj.setDescricao(rst.getString("p.descricao"));
                obj.setCor(rst.getString("p.cor"));
                obj.setPreco(rst.getDouble("preco"));
                obj.setQtd_estoque(rst.getInt("qtd_estoque"));
                f.setNome(rst.getString("f.nome"));
                obj.setFornecedores(f);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }

    public Produtos buscaProdutoPeloCodigo(int id) {
        try {
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select p.id, p.descricao, p.cor, p.tamanho, p.preco, p.qtd_estoque, f.nome "
                    + "from bdvendas.tb_produtos as p "
                    + "inner join bdvendas.tb_fornecedores as f on (p.for_id = f.id) where p.id = ?";

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {

                obj.setId(rst.getInt("p.id"));
                obj.setDescricao(rst.getString("p.descricao"));
                obj.setTamanho(rst.getString("tamanho"));
                obj.setCor(rst.getString("p.cor"));
                obj.setPreco(rst.getDouble("p.preco"));
                obj.setQtd_estoque(rst.getInt("p.qtd_estoque"));
                f.setNome(rst.getString("f.nome"));
                obj.setFornecedores(f);

            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }
    
       //Metodo que da baixa no estoque
    public void baixaEstoque(int id, int qtd_novo){
        try {
            String sql = "update tb_produtos set qtd_estoque =? where id=?";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, qtd_novo);
            pstm.setInt(2, id);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
    }
    
    //Metodo que retorna  o estrouqe atual
    public int retornaEstoqueAtual(int id){
        try {
            int qtd_estoque = 0;
            
            String sql = "select qtd_estoque from tb_produtos where id = ?";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, id);
            
            rst = pstm.executeQuery();
            
            if(rst.next()){
                Produtos p = new Produtos();
                
                qtd_estoque = (rst.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void adicionarEstoque(int id,int qtd_novo){
        try {
            String sql = "update tb_produtos set qtd_estoque = ? where id = ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, qtd_novo);
            pstm.setInt(2, id);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
}
