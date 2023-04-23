/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;

//Importação das bibliotecas e pacotes
import br.com.shift.projetovendas.Model.Clientes;
import br.com.shift.projetovendas.jdbc.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author robsonPaiva
 */
public class ClienteDao {

    private PreparedStatement pstm;
    private ResultSet rst;
    private final Connection con;

    //Contrutor padrão para conectar automaticamento com o banco de dados
    public ClienteDao() {
        this.con = new ConnectionFactory().getConnection();

    }

    //Metodo cadastra Cliente
    public void cadastrarCliente(Clientes obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "INSERT into tb_clientes(nome,pontoReferencia,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getPontoRef());
            pstm.setString(3, obj.getCpf());
            pstm.setString(4, obj.getEmail());
            pstm.setString(5, obj.getTelefone());
            pstm.setString(6, obj.getCelular());
            pstm.setString(7, obj.getCep());
            pstm.setString(8, obj.getEndereco());
            pstm.setInt(9, obj.getNumero());
            pstm.setString(10, obj.getComplemento());
            pstm.setString(11, obj.getBairro());
            pstm.setString(12, obj.getCidade());
            pstm.setString(13, obj.getEstado());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
    
     //Metodo editar Cliente
    public void alterarCliente(Clientes obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "update tb_clientes set nome=?,pontoReferencia=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    + "complemento=?,bairro=?,cidade=?,estado=? where id = ?";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getPontoRef());
            pstm.setString(3, obj.getCpf());
            pstm.setString(4, obj.getEmail());
            pstm.setString(5, obj.getTelefone());
            pstm.setString(6, obj.getCelular());
            pstm.setString(7, obj.getCep());
            pstm.setString(8, obj.getEndereco());
            pstm.setInt(9, obj.getNumero());
            pstm.setString(10, obj.getComplemento());
            pstm.setString(11, obj.getBairro());
            pstm.setString(12, obj.getCidade());
            pstm.setString(13, obj.getEstado());
            pstm.setInt(14, obj.getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Cliente Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    //Metodo excluir Cliente
    public void excluirCliente(Clientes obj) {
        try {

            //Comando SQL para deletar um registro
            String sql = "delete from tb_clientes where id = ?";
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
    public List listaClientes() {
        try {
            List<Clientes> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_clientes";

            pstm = con.prepareStatement(sql);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Clientes obj = new Clientes();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoReferencia"));
                obj.setCpf(rst.getString("cpf"));
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

    public List buscaClientePeloNome(String nome) {
        try {
            List<Clientes> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_clientes where nome like ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Clientes obj = new Clientes();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoReferencia"));
                obj.setCpf(rst.getString("cpf"));
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

    public Clientes consultaPorCpf(String cpf) {
        try {
            String sql = "select * from tb_clientes where cpf = ?";
            Clientes obj = new Clientes();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, cpf);

            rst = pstm.executeQuery();
            if (rst.next()) {

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoReferencia"));
                obj.setCpf(rst.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado" + erro);
            return null;
        }
    }

    public Clientes consultaPorNome(String nome) {
        try {
            String sql = "select * from tb_clientes where cpf = ?";
            Clientes obj = new Clientes();

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);

            rst = pstm.executeQuery();
            if (rst.next()) {

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoReferencia"));
                obj.setCpf(rst.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado" + erro);
            return null;
        }
    }

   
    
    
    public String buscaCpfPorNome(String nome){
        String cpf = "";
        try {
            String sql = "select cpf from tb_clientes where nome = ?";
            
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);
            
            rst = pstm.executeQuery();
            if(rst.next()){
                cpf = (rst.getString("cpf"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro ao procurar o cpf pelo nome" + e);
        }
        
        return cpf;
    }

}
