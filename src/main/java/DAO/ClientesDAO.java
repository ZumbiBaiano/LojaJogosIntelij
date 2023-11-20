package DAO;

import Model.Clientes;
import java.sql.Connection;
import Factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClientesDAO {

    /*
     *CRUD
     *C: CREATE (CRIAÇÃO)
     *R: READ (LEITURA)
     *U: UPDATE (aATUALIZAÇÃO)
     *D: DELETE (APAGAR)
     */


    public void save(Clientes clientes) {


        String sql = "INSERT INTO clientes(id_cliente, nome_cliente, cpf ,email , endereco) VALUES (?, ?, ?, ? , ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criames uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores que são esperados pela query
            pstm.setString(1, clientes.getId_cliente());
            pstm.setString(1, clientes.getNome_cliente());
            pstm.setString(1, clientes.getCpf());
            pstm.setString(2, clientes.getEmail());
            pstm.setString(3,(clientes.getEndereco()));

            //Executar a query
            pstm.execute();

            System.out.println("cliente salvo com sucesso!");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            //Fechar as conexões
            try {
                if(pstm != null) {
                    pstm.close();
                }

                if(conn != null) {
                    conn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Clientes clientes) {

        String sql = "UPDATE cliente SET nome_cliente = ?, idade = ?, dataCadastro = ?"
                +"WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            //Criar conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criar a classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adiciona valores para atualizar
            pstm.setString(1, clientes.getNome_cliente());
            pstm.setString(2, clientes.getCpf());
            pstm.setString(3, clientes.getEmail());

            //Qual o ID do registro que deseja atualizar
            pstm.setString(4, clientes.getId_cliente());

            //Executando a query
            pstm.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }

                if(conn != null) {
                    conn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteByID(int id) {

        String sql = "DELETE FROM clientes WHERE id = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }

                if(conn != null) {
                    conn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Clientes> getCliente() {
        String sql = "SELECT * FROM contatos";
        List<Clientes> clientes = new ArrayList<Clientes>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Clientes clientes2 = new Clientes();

                //Recupera o id
                clientes2.setCpf(rset.getString("id"));

                //Recupera o nome
                clientes2.setNome_cliente(rset.getString("nome"));

                //Recupera o Cpf
                clientes2.setCpf(rset.getString("Cpf"));

                //Recupera a dataCadastro
                clientes2.setEmail(rset.getString("email"));


                clientes.add(clientes2);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }
}
