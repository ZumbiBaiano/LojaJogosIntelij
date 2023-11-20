package DAO;

import java.sql.Connection;
import Factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Pedidos;

public class PedidosDAO {

    /*
     *CRUD
     *C: CREATE (CRIAÇÃO)
     *R: READ (LEITURA)
     *U: UPDATE (aATUALIZAÇÃO)
     *D: DELETE (APAGAR)
     */


    public void save(Pedidos Pedidos) {


        String sql = "INSERT INTO Pedidos(id_produto, nome_produto) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criames uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores que são esperados pela query
            pstm.setString(1, Pedidos.getId_cliente());
            pstm.setString(2, Pedidos.getId_jogo());
            pstm.setString(2, Pedidos.getEndereco());
            pstm.setDouble(2, Pedidos.getPreco());
            pstm.setString(2, Pedidos.getPlataforma());


            //Executar a query
            pstm.execute();

            System.out.println("Catalogo salvo com sucesso!");
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

    public void update(Pedidos Pedidos) {

        String sql = "UPDATE cliente SET nome = ?, idade = ?, dataCadastro = ?"
                +"WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            //Criar conexão com o banco
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criar a classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adiciona valores para atualizar
            pstm.setString(1, Pedidos.getId_cliente());
            pstm.setString(2, Pedidos.getId_jogo());

            //Qual o ID do registro que deseja atualizar
            pstm.setString(4, Pedidos.getId_jogo());

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

    public List<Pedidos> getCliPedidos() {
        String sql = "SELECT * FROM contatos";
        List<Pedidos> Pedidos = new ArrayList<Pedidos>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Pedidos Pedidos2 = new Pedidos();

                //Recupera o id
                //Catalogo_de_Jogos2.setId_jogo(rset.getString("id"));

                //Recupera o nome
                //Catalogo_de_Jogos2.setNome_jogo(rset.getString("nome"));

                //Recupera o Cpf
                //.setPreco(rset.getDouble("Cpf"));

                //Recupera a dataCadastro

                //Catalogo_de_Jogos.add(Catalogo_de_Jogos2);

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

        return Pedidos;
    }
}
