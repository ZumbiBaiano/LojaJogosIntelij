package DAO;

import java.sql.Connection;
import Factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.CatalogoDeJogos;

public class CatalogoDeJogosDAO {

    /*
     *CRUD
     *C: CREATE (CRIAÇÃO)
     *R: READ (LEITURA)
     *U: UPDATE (aATUALIZAÇÃO)
     *D: DELETE (APAGAR)
     */


    public void save(CatalogoDeJogos CatalogoDeJogos) {


        String sql = "INSERT INTO Catalogo_de_Jogos(id_jogo, nome_jogo, preco, classificacao_idade, plataforma) VALUES (?, ?, ?, ? , ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criames uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores que são esperados pela query
            pstm.setString(1, CatalogoDeJogos.getNome_jogo());
            pstm.setString(2, CatalogoDeJogos.getId_jogo());
            pstm.setDouble(3,(CatalogoDeJogos.getPreco()));
            pstm.setInt(2, CatalogoDeJogos.getClassificacao_idade());
            pstm.setString(2, CatalogoDeJogos.getPlataforma());

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

    public void update(CatalogoDeJogos catalogoDeJogos) {

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
            pstm.setString(2, catalogoDeJogos.getId_jogo());;
            pstm.setString(2, catalogoDeJogos.getNome_jogo());
            pstm.setDouble(2, catalogoDeJogos.getPreco());
            pstm.setInt(2, catalogoDeJogos.getClassificacao_idade());
            pstm.setString(2, catalogoDeJogos.getPlataforma());

            //Qual o ID do registro que deseja atualizar
            pstm.setString(4, catalogoDeJogos.getId_jogo());

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

    public List<CatalogoDeJogos> getCliCatalogo_de_Jogos() {
        String sql = "SELECT * FROM contatos";
        List<CatalogoDeJogos> CatalogoDeJogos = new ArrayList<CatalogoDeJogos>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                CatalogoDeJogos catalogoDeJogos = new CatalogoDeJogos();

                //Recupera o id
                catalogoDeJogos.setId_jogo(rset.getString("id"));

                //Recupera o nome
                catalogoDeJogos.setNome_jogo(rset.getString("nome"));

                //Recupera o Cpf
                catalogoDeJogos.setPreco(rset.getDouble("Cpf"));

                //Recupera a dataCadastro

                CatalogoDeJogos.add(catalogoDeJogos);

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

        return CatalogoDeJogos;
    }
}
