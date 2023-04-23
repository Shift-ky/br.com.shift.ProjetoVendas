/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;

import br.com.shift.projetovendas.Model.Fornecedores;
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
public class FornecedoresDao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;

    //Contrutor padrão para conectar automaticamento com o banco de dados
    public FornecedoresDao() {
        this.con = new ConnectionFactory().getConnection();

    }
    
     //Metodo cadastra Cliente
    public void cadastraFornecedores(Fornecedores obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "INSERT into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,"
                    + "bairro,cidade,estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getCnpj());
            pstm.setString(3, obj.getEmail());
            pstm.setString(4, obj.getTelefone());
            pstm.setString(5, obj.getCelular());
            pstm.setString(6, obj.getCep());
            pstm.setString(7, obj.getEndereco());
            pstm.setInt(8, obj.getNumero());
            pstm.setString(9, obj.getComplemento());
            pstm.setString(10, obj.getBairro());
            pstm.setString(11, obj.getCidade());
            pstm.setString(12, obj.getEstado());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //Metodo editar Cliente
    public void alterarFornecedores(Fornecedores obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    + "complemento=?,bairro=?,cidade=?,estado=? where id = ?";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getCnpj());
            pstm.setString(3, obj.getEmail());
            pstm.setString(4, obj.getTelefone());
            pstm.setString(5, obj.getCelular());
            pstm.setString(6, obj.getCep());
            pstm.setString(7, obj.getEndereco());
            pstm.setInt(8, obj.getNumero());
            pstm.setString(9, obj.getComplemento());
            pstm.setString(10, obj.getBairro());
            pstm.setString(11, obj.getCidade());
            pstm.setString(12, obj.getEstado());
            pstm.setInt(13, obj.getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Fornecedor Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    //Metodo excluir Cliente
    public void excluirFornecedores(Fornecedores obj) {
        try {

            //Comando SQL para deletar um registro
            String sql = "delete from tb_fornecedores where id = ?";
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
    public List listaFornecedores() {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_fornecedores";

            pstm = con.prepareStatement(sql);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setEstado(rst.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }

    public List buscaForncedorPeloNome(String nome) {
        try {
            List<Fornecedores> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_fornecedores where nome like ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setCidade(rst.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }

    public Fornecedores consultaPorCnpj(String cnpj) {
        try {
            String sql = "select * from tb_fornecedores where cnpj = ?";
            Fornecedores obj = new Fornecedores();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, cnpj);

            rst = pstm.executeQuery();
            if (rst.next()) {
                
                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setCidade(rst.getString("estado"));

            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado" + erro);
            return null;
        }
    }
    
    public Fornecedores consultaPorNome(String nome) {
        try {
            String sql = "select * from tb_fornecedores where nome = ?";
            Fornecedores obj = new Fornecedores();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);

            rst = pstm.executeQuery();
            if (rst.next()) {
                
                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setCnpj(rst.getString("cnpj"));
                obj.setEmail(rst.getString("email"));
                obj.setTelefone(rst.getString("telefone"));
                obj.setCelular(rst.getString("celular"));
                obj.setCep(rst.getString("cep"));
                obj.setEndereco(rst.getString("endereco"));
                obj.setNumero(rst.getInt("numero"));
                obj.setComplemento(rst.getString("complemento"));
                obj.setBairro(rst.getString("bairro"));
                obj.setCidade(rst.getString("cidade"));
                obj.setCidade(rst.getString("estado"));

            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado" + erro);
            return null;
        }
    }

}
