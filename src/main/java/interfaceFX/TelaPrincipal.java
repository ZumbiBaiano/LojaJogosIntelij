package interfaceFX;

import DAO.CatalogoDeJogosDAO;
import Model.CatalogoDeJogos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TelaPrincipal extends Application {
    private ListView<String> jogosListView;
    private List<CatalogoDeJogos> allJogos;
    private TelaPedidos telaPedidos; // Adicionado

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catálogo de Jogos");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 400, 300);

        Label titleLabel = new Label("Catálogo de Jogos");

        // Barra De Pesquisa
        TextField searchField = new TextField();
        searchField.setPromptText("Pesquisar por nome do jogo");



        jogosListView = new ListView<>();
        Button editButton = new Button("Editar Jogo");
        Button removeButton = new Button("Remover Jogo");
        Button refreshButton = new Button("Atualizar");
        Button selectJogoButton = new Button("Selecionar Jogo");


        CatalogoDeJogosDAO catalogoDao = new CatalogoDeJogosDAO();
        allJogos = catalogoDao.getCatalogoDeJogos();
        atualizarListaJogos();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            List<CatalogoDeJogos> jogosFiltrados = allJogos.stream()
                    .filter(jogo -> jogo.getNome_jogo().toLowerCase().contains(newValue.toLowerCase()))
                    .toList();

            jogosListView.getItems().clear();
            for (CatalogoDeJogos jogo : jogosFiltrados) {
                jogosListView.getItems().add("ID: " + jogo.getId_jogo() + " - Nome: " + jogo.getNome_jogo());
            }
        });

        editButton.setOnAction(e -> {

            if (!jogosListView.getSelectionModel().isEmpty()) {
                String selectedJogo = jogosListView.getSelectionModel().getSelectedItem();


                if (selectedJogo != null) {

                    Integer id = extrairId_Jogo(selectedJogo);

                    if (id != null) {

                        CatalogoDeJogos jogo = allJogos.stream()
                                .filter(j -> Integer.valueOf(id).equals(j.getId_jogo()))
                                .findFirst()
                                .orElse(null);

                        if (jogo != null) {
                            abrirJanelaEdicao(jogo, catalogoDao);
                        }
                    }
                }
            }
        });

//        removeButton.setOnAction(e -> {
//
//            if (!jogosListView.getSelectionModel().isEmpty()) {
//                String selectedJogo = jogosListView.getSelectionModel().getSelectedItem();
//
//
//                if (selectedJogo != null) {
//
//                    Integer id = extrairId_Jogo(selectedJogo);
//
//                    if (id != null) {
//
//                        CatalogoDeJogos jogo = allJogos.stream()
//                                .filter(j -> Integer.valueOf(id).equals(j.getId_jogo()))
//                                .findFirst()
//                                .orElse(null);
//
//                        if (jogo != null) {
//
//                            catalogoDao.delete(jogo);
//
//                            //remove o jogo
//                            allJogos.remove(jogo);
//
//                            // Atualize a lista de jogos na tela
//                            atualizarListaJogos();
//                        }
//                    }
//                }
//            }
//        });

        removeButton.setOnAction(e -> {
            String selectedPaciente = jogosListView.getSelectionModel().getSelectedItem();
            if (selectedPaciente != null) {
                int prontuario = extrairId_Jogo(selectedPaciente);
                CatalogoDeJogosDAO.deleteByPK(prontuario);
                // Remova o paciente da lista local
                String id_jogo = null;
                CatalogoDeJogos pacienteARemover = allJogos.stream()
                        .filter(p -> p.getId_jogo() == id_jogo)
                        .findFirst()
                        .orElse(null);

                if (pacienteARemover != null) {
                    allJogos.remove(pacienteARemover);
                }
                atualizarListaJogos();
            }
        });

        refreshButton.setOnAction(e -> {
            // Atualiza os jogos do catalogo
            allJogos = catalogoDao.getCatalogoDeJogos();
            atualizarListaJogos();
        });

        telaPedidos = new TelaPedidos(); //
        selectJogoButton.setOnAction(e -> {
            // Adiciona o jogo selecionado à telaPedidos
            if (!jogosListView.getSelectionModel().isEmpty()) {
                String selectedJogo = jogosListView.getSelectionModel().getSelectedItem();
                telaPedidos.adicionarJogoSelecionado(selectedJogo);

                // Redirecione para a telaPedidos
                Stage telaPedidosStage = new Stage();
                telaPedidos.start(telaPedidosStage);
            }
        });

        telaPedidos = new TelaPedidos(); // Adicionado

        root.getChildren().addAll(titleLabel, searchField, jogosListView, editButton, removeButton, refreshButton, selectJogoButton);

        stage.setScene(scene);
        stage.show();
    }

    private void atualizarListaJogos() {
        jogosListView.getItems().clear();
        for (CatalogoDeJogos jogo : allJogos) {
            jogosListView.getItems().add("ID: " + jogo.getId_jogo() + " - Nome: " + jogo.getNome_jogo());
        }
    }

    private int extrairId_Jogo(String jogoString) {
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

    private void abrirJanelaEdicao(CatalogoDeJogos jogo, CatalogoDeJogosDAO catalogoDao) {
        Stage stage = new Stage();
        stage.setTitle("Editar Jogo");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 400, 300);

        Label titleLabel = new Label("Editar Jogo");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Jogo");
        nomeField.setText(jogo.getNome_jogo());

        TextField precoField = new TextField();
        precoField.setPromptText("Preço");
        precoField.setText(String.valueOf(jogo.getPreco()));

        TextField classificacaoField = new TextField();
        classificacaoField.setPromptText("Classificação de Idade");
        classificacaoField.setText(String.valueOf(jogo.getClassificacao_idade()));

        TextField plataformaField = new TextField();
        plataformaField.setPromptText("Plataforma");
        plataformaField.setText(jogo.getPlataforma());

        Button salvarButton = new Button("Salvar");

        salvarButton.setOnAction(e -> {

            jogo.setNome_jogo(nomeField.getText());
            jogo.setPreco(Double.parseDouble(precoField.getText()));
            jogo.setClassificacao_idade(Integer.parseInt(classificacaoField.getText()));
            jogo.setPlataforma(plataformaField.getText());

            // Chame o método de atualização do DAO
            catalogoDao.update(jogo);

            // Feche a janela de edição
            stage.close();

            // Atualize a lista de jogos na tela principal, se necessário
            atualizarListaJogos();
        });

        root.getChildren().addAll(titleLabel, nomeField, precoField, classificacaoField, plataformaField, salvarButton);

        stage.setScene(scene);
        stage.show();
    }
}