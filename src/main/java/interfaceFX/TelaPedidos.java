package interfaceFX;

import DAO.CatalogoDeJogosDAO;
import DAO.PedidosDAO;
import Model.CatalogoDeJogos;
import Model.Pedidos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TelaPedidos extends Application {
    private ListView<String> pedidosListView;
    private List<Pedidos> allPedidos;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pedidos");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 600, 400);

        Label titleLabel = new Label("Lista de Pedidos");

        pedidosListView = new ListView<>();
        Button deleteButton = new Button("Excluir Pedido");


        PedidosDAO pedidosDAO = new PedidosDAO();
        allPedidos = pedidosDAO.getPedidos();
        atualizarListaPedidos();

        deleteButton.setOnAction(e -> {

            if (!pedidosListView.getSelectionModel().isEmpty()) {
                String selectedPedido = pedidosListView.getSelectionModel().getSelectedItem();


                if (selectedPedido != null) {

                    Integer id = extrairIdPedido(selectedPedido);

                    if (id != null) {
                        // Utilize Integer.equals para comparar Integer
                        Pedidos pedido = allPedidos.stream()
                                .filter(p -> Integer.valueOf(id).equals(p.getId_pedido()))
                                .findFirst()
                                .orElse(null);

                        if (pedido != null) {
                            // Chame o método de exclusão do DAO
                            pedidosDAO.deleteByID(pedido.getId_pedido());

                            // Atualize a lista de pedidos na tela, se necessário
                            allPedidos = pedidosDAO.getPedidos();
                            atualizarListaPedidos();
                        }
                    }
                }
            }
        });

        root.getChildren().addAll(titleLabel, pedidosListView, deleteButton);

        stage.setScene(scene);
        stage.show();
    }

    private void atualizarListaPedidos() {
        pedidosListView.getItems().clear();
        for (Pedidos pedido : allPedidos) {
            pedidosListView.getItems().add(formatarPedido(pedido));
        }
    }

    private Integer extrairIdPedido(String pedidoString) {
        int idStartIndex = pedidoString.indexOf("ID: ") + 4;
        int idEndIndex = pedidoString.indexOf(" -");

        if (idStartIndex >= 0 && idEndIndex >= 0 && idStartIndex < idEndIndex) {
            try {
                String idStr = pedidoString.substring(idStartIndex, idEndIndex);
                return Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                // Lidar com a exceção de conversão de string para inteiro
                e.printStackTrace(); // ou outra forma de tratamento
                return null; // ou outra indicação de erro
            }
        } else {
            return null;
        }
    }

    // Método para formatar um pedido como string
    private String formatarPedido(Pedidos pedido) {
        return "ID: " + pedido.getId_pedido() +
                " - Cliente: " + pedido.getId_cliente() +
                " - Jogo: " + pedido.getId_jogo() +
                " - Endereço: " + pedido.getEndereco() +
                " - Preço: " + pedido.getPreco() +
                " - Plataforma: " + pedido.getPlataforma();
    }

    // Método para adicionar um jogo selecionado ao ListView
    public void adicionarJogoSelecionado(String selectedJogo) {
        if (selectedJogo != null) {
            // Extrair o ID do Jogo do texto
            Integer idJogo = extrairId_Jogo(selectedJogo);

            // Procurar o jogo pelo ID na lista de jogos
            CatalogoDeJogosDAO catalogoDao = new CatalogoDeJogosDAO();
            CatalogoDeJogos jogo = catalogoDao.getId_jogo(idJogo);

            // Adicionar o jogo à lista de pedidos, se encontrado
            if (jogo != null) {
                // Crie um pedido fictício com as informações do jogo
                Pedidos pedidoFicticio = new Pedidos();
                pedidoFicticio.setId_jogo(jogo.getId_jogo());
                pedidoFicticio.setPreco(jogo.getPreco());
                pedidoFicticio.setPlataforma(jogo.getPlataforma());
                pedidoFicticio.setEndereco("Endereço do Cliente"); // Substitua pelo endereço real do cliente
                pedidoFicticio.setId_cliente(String.valueOf(1)); // Substitua pelo ID real do cliente

                // Adicione o pedido fictício à lista de pedidos
                PedidosDAO pedidosDAO = new PedidosDAO();
                pedidosDAO.insert(pedidoFicticio);

                // Atualize a lista de pedidos na tela
                allPedidos = pedidosDAO.getPedidos();
                atualizarListaPedidos();
            }
        }
    }

    private Integer extrairId_Jogo(String jogoString) {
        int idStartIndex = jogoString.indexOf("ID: ") + 4;
        int idEndIndex = jogoString.indexOf(" - Nome: ");

        if (idStartIndex >= 0 && idEndIndex >= 0 && idStartIndex < idEndIndex) {
            try {
                String idStr = jogoString.substring(idStartIndex, idEndIndex);
                return Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                // Lidar com a exceção de conversão de string para inteiro
                e.printStackTrace(); // ou outra forma de tratamento
                return -1; // ou outra indicação de erro
            }
        } else {
            return -1;
        }
    }
}