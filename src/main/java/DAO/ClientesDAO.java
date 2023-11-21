package DAO;

import Factory.ConnectionFactory;
import Model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClientesDAO {

    // ... (outros métodos)

    public void save(Clientes clientes) {
        String sql = "INSERT INTO clientes(nome_cliente, cpf, email, endereco, senha) VALUES (?, ?, ?, ?, ?)";

        try (
                java.sql.Connection conn = ConnectionFactory.createConnectionToMySQL()
        ) {
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, clientes.getNome_cliente());
                pstm.setString(2, clientes.getCpf());
                pstm.setString(3, clientes.getEmail());
                pstm.setString(4, clientes.getEndereco());
                pstm.setString(5, clientes.getSenha());

                pstm.executeUpdate();  // Use executeUpdate() para operações de INSERT, UPDATE, DELETE

                System.out.println("Cliente salvo com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Clientes clientes) {
        String sql = "UPDATE clientes SET nome_cliente = ?, cpf = ?, email = ?, endereco = ? WHERE id_cliente = ?";

        try (
                java.sql.Connection conn = ConnectionFactory.createConnectionToMySQL()
        ) {
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, clientes.getNome_cliente());
                pstm.setString(2, clientes.getCpf());
                pstm.setString(3, clientes.getEmail());
                pstm.setString(4, clientes.getEndereco());
                pstm.setInt(5, clientes.getId_cliente());

                pstm.executeUpdate();

                System.out.println("Cliente atualizado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Clientes> getCliente() {
        String sql = "SELECT * FROM clientes";
        List<Clientes> clientes = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rset = pstm.executeQuery()
        ) {
            while (rset.next()) {
                Clientes cliente = new Clientes();

                cliente.setId_cliente(rset.getInt("id_cliente"));
                cliente.setNome_cliente(rset.getString("nome_cliente"));
                cliente.setCpf(rset.getString("cpf"));
                cliente.setEmail(rset.getString("email"));
                cliente.setEndereco(rset.getString("endereco"));
                cliente.setSenha(rset.getString("senha"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
