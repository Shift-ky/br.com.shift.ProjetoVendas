/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Dao;

import br.com.shift.projetovendas.Model.Funcionarios;
import br.com.shift.projetovendas.View.FrameLogin;
import br.com.shift.projetovendas.View.FrameMenu;
import br.com.shift.projetovendas.jdbc.ConnectionFactory;
//import java.awt.color;
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
public class FuncionariosDao {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    
    public FuncionariosDao(){
        this.con = new ConnectionFactory().getConnection();
        
    }
    
    //Metodo cadastra Funcionarios
    public void cadastraFuncionario(Funcionarios obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "INSERT into tb_funcionarios(nome,pontoRef,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getPontoRef());
            pstm.setString(3, obj.getCpf());
            pstm.setString(4, obj.getEmail());
            pstm.setString(5, obj.getSenha());
            pstm.setString(6, obj.getCargo());
            pstm.setString(7, obj.getNivel_acesso());
            pstm.setString(8, obj.getTelefone());
            pstm.setString(9, obj.getCelular());
            pstm.setString(10, obj.getCep());
            pstm.setString(11, obj.getEndereco());
            pstm.setInt(12, obj.getNumero());
            pstm.setString(13, obj.getComplemento());
            pstm.setString(14, obj.getBairro());
            pstm.setString(15, obj.getCidade());
            pstm.setString(16, obj.getEstado());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Funcionario Cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //Metodo listar Funcionarios
    public List listaFuncionarios() {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_funcionarios";

            pstm = con.prepareStatement(sql);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoRef"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));
                obj.setSenha(rst.getString("senha"));
                obj.setCargo(rst.getString("cargo"));
                obj.setNivel_acesso(rst.getString("nivel_acesso"));
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

    //Metodo excluir Funcionarios
    public void excluirFuncionarios(Funcionarios obj) {
        try {

            //Comando SQL para deletar um registro
            String sql = "delete from tb_funcionarios where id = ?";
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

    //Metodo editar Cliente
    public void alterarFuncionarios(Funcionarios obj) {
        try {
            //Comando sql para inserir dados no banco de dados
            String sql = "update tb_funcionarios set nome=?,pontoRef=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,"
                    + "complemento=?,bairro=?,cidade=?,estado=? where id = ?";

            //Conectar com o banco de dados e setar os dados para serem salvo no banco de dados
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getNome());
            pstm.setString(2, obj.getPontoRef());
            pstm.setString(3, obj.getCpf());
            pstm.setString(4, obj.getEmail());
            pstm.setString(5, obj.getSenha());
            pstm.setString(6, obj.getCargo());
            pstm.setString(7, obj.getNivel_acesso());
            pstm.setString(8, obj.getTelefone());
            pstm.setString(9, obj.getCelular());
            pstm.setString(10, obj.getCep());
            pstm.setString(11, obj.getEndereco());
            pstm.setInt(12, obj.getNumero());
            pstm.setString(13, obj.getComplemento());
            pstm.setString(14, obj.getBairro());
            pstm.setString(15, obj.getCidade());
            pstm.setString(16, obj.getEstado());
            pstm.setInt(17, obj.getId());

            //Executar o comando SQL
            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Funncionario Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }

    }

    public List buscaFuncionarioPeloNome(String nome) {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_funcionarios where nome like ?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, nome);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoRef"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));

                obj.setSenha(rst.getString("senha"));
                obj.setCargo(rst.getString("cargo"));
                obj.setNivel_acesso(rst.getString("nivel_acesso"));

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

    //Metodo efetua login
    public void efetarLogar(String email, String senha) {
        try {
            String sql = "select * from tb_funcionarios where email = ? and senha = ?";

            pstm = con.prepareStatement(sql);

            pstm.setString(1, email);
            pstm.setString(2, senha);

            rst = pstm.executeQuery();

            if (rst.next()) {

                //Caso o funcionario for adm
                if (rst.getString("nivel_acesso").equals("Administrador")) {
                    //Usuário logado
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema");
                    FrameMenu frameMenu = new FrameMenu();
//                    frameMenu.usuarioLogado = rst.getString("nome");
//                    frameMenu.id_logado = rst.getInt("id");
//                    frameMenu.setVisible(true);
                    

                } else if (rst.getString("nivel_acesso").equals("Usuário")) {
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema");
                    FrameMenu frameMenu = new FrameMenu();
//                    frameMenu.usuarioLogado = rst.getString("nome");
//                    frameMenu.id_logado = rst.getInt("id");

                    //desabilitando os menus
//                    frameMenu.menuHistoricoVendas.setEnabled(false);
//                    frameMenu.menuPosicao.setEnabled(false);
//                    frameMenu.menuFuncionario.setEnabled(false);
//                    frameMenu.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Dados incorretos");
//                new FrameLogin().setBackground(Color.red);
                //Dados incorretos
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro " + erro);
        }

    }

    public Funcionarios buscaFuncionarioPeloId(int id) {
        try {

            //Comando sql para selecionar todos os dados do banco de dados da tabela clientes
            String sql = "select * from tb_funcionarios where id = ?";
            Funcionarios obj = new Funcionarios();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            //Obj que vai receber a lista com todos os dados da tabela cliente
            rst = pstm.executeQuery();
            while (rst.next()) {

                obj.setId(rst.getInt("id"));
                obj.setNome(rst.getString("nome"));
                obj.setPontoRef(rst.getString("pontoRef"));
                obj.setCpf(rst.getString("cpf"));
                obj.setEmail(rst.getString("email"));

                obj.setSenha(rst.getString("senha"));
                obj.setCargo(rst.getString("cargo"));
                obj.setNivel_acesso(rst.getString("nivel_acesso"));

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
            JOptionPane.showMessageDialog(null, "ERRO" + erro);
            return null;
        }

    }
    
    public boolean dispose(String email, String senha){
        String sql = "select * from tb_funcionarios where email = ? and senha = ?";
        boolean desligar = false;
        try {
            
            pstm = con.prepareStatement(sql);

            pstm.setString(1, email);
            pstm.setString(2, senha);

            rst = pstm.executeQuery();

            if (rst.next()) {
             desligar = true;
            }
        } catch (SQLException e) {
            
        }
        return desligar;
    }

}
