package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.CatalogoDeJogos;
import Factory.ConnectionFactory;

public class CatalogoDeJogosDAO {

    public static void deleteByPK(int prontuario) {
    }

    public void save(CatalogoDeJogos catalogoDeJogos) {
        String sql = "INSERT INTO Catalogo_de_Jogos(id_jogo, nome_jogo, preco, classificacao_idade, plataforma) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            // Se id_jogo for um número, use setInt
            pstm.setString(1, catalogoDeJogos.getId_jogo());
            pstm.setString(2, catalogoDeJogos.getNome_jogo());
            pstm.setDouble(3, catalogoDeJogos.getPreco());
            pstm.setInt(4, catalogoDeJogos.getClassificacao_idade());
            pstm.setString(5, catalogoDeJogos.getPlataforma());

            pstm.execute();

            System.out.println("Catálogo de jogos salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(CatalogoDeJogos catalogoDeJogos) {
        String sql = "UPDATE Catalogo_de_Jogos SET nome_jogo = ?, preco = ?, classificacao_idade = ?, plataforma = ? WHERE id_jogo = ?";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, catalogoDeJogos.getNome_jogo());
            pstm.setDouble(2, catalogoDeJogos.getPreco());
            pstm.setInt(3, catalogoDeJogos.getClassificacao_idade());
            pstm.setString(4, catalogoDeJogos.getPlataforma());
            pstm.setString(5, catalogoDeJogos.getId_jogo());

            pstm.execute();

            System.out.println("Catálogo de jogos atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(String id) {
        String sql = "DELETE FROM Catalogo_de_Jogos WHERE id_jogo = ?";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CatalogoDeJogos> getCatalogoDeJogos() {
        String sql = "SELECT * FROM Catalogo_de_Jogos";
        List<CatalogoDeJogos> catalogoDeJogosList = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rset = pstm.executeQuery()
        ) {
            while (rset.next()) {
                CatalogoDeJogos catalogoDeJogos = new CatalogoDeJogos();
                catalogoDeJogos.setId_jogo(rset.getString("id_jogo"));
                catalogoDeJogos.setNome_jogo(rset.getString("nome_jogo"));
                catalogoDeJogos.setPreco(rset.getDouble("preco"));
                catalogoDeJogos.setClassificacao_idade(rset.getInt("classificacao_idade"));
                catalogoDeJogos.setPlataforma(rset.getString("plataforma"));

                catalogoDeJogosList.add(catalogoDeJogos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return catalogoDeJogosList;
    }

    public void delete(CatalogoDeJogos jogo) {
    }

    public CatalogoDeJogos getId_jogo(Integer idJogo) {
        return null;
    }
}
