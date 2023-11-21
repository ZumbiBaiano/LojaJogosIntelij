package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Factory.ConnectionFactory;
import Model.Pedidos;

public class PedidosDAO {

    public void save(Pedidos pedido) {
        String sql = "INSERT INTO Pedidos(id_cliente, id_jogo, endereco, preco, plataforma) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, pedido.getId_cliente());
            pstm.setString(2, pedido.getId_jogo());
            pstm.setString(3, pedido.getEndereco());
            pstm.setDouble(4, pedido.getPreco());
            pstm.setString(5, pedido.getPlataforma());

            pstm.execute();

            System.out.println("Pedido salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Pedidos pedido) {
        String sql = "UPDATE Pedidos SET id_cliente = ?, id_jogo = ?, endereco = ?, preco = ?, plataforma = ? WHERE id_pedido = ?";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, pedido.getId_cliente());
            pstm.setString(2, pedido.getId_jogo());
            pstm.setString(3, pedido.getEndereco());
            pstm.setDouble(4, pedido.getPreco());
            pstm.setString(5, pedido.getPlataforma());
            pstm.setInt(6, pedido.getId_pedido());

            pstm.execute();

            System.out.println("Pedido atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id) {
        String sql = "DELETE FROM Pedidos WHERE id_pedido = ?";

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setInt(1, id);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pedidos> getPedidos() {
        String sql = "SELECT * FROM Pedidos";
        List<Pedidos> pedidosList = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rset = pstm.executeQuery()
        ) {
            while (rset.next()) {
                Pedidos pedido = new Pedidos();
                pedido.setId_pedido(rset.getInt("id_pedido"));
                pedido.setId_cliente(rset.getString("id_cliente"));
                pedido.setId_jogo(rset.getString("id_jogo"));
                pedido.setEndereco(rset.getString("endereco"));
                pedido.setPreco(rset.getDouble("preco"));
                pedido.setPlataforma(rset.getString("plataforma"));

                pedidosList.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidosList;
    }

    public void insert(Pedidos pedidoFicticio) {
    }
}
