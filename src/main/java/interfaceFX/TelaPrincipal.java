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
import java.util.stream.Collectors;

public class TelaPrincipal extends Application {
    private ListView<String> jogosListView;
    private List<CatalogoDeJogos> allJogos;

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

        // Campo de pesquisa
        TextField searchField = new TextField();
        searchField.setPromptText("Pesquisar por nome do jogo");

        jogosListView = new ListView<>();
        Button editButton = new Button("Editar Jogo");
        Button removeButton = new Button("Remover Jogo");
        Button refreshButton = new Button("Atualizar");
        Button newJogoButton = new Button("Novo Jogo");

        // Popule a ListView com os jogos do banco de dados
        CatalogoDeJogosDAO catalogoDao = new CatalogoDeJogosDAO();
        allJogos = catalogoDao.getCliCatalogo_de_Jogos();
        atualizarListaJogos();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Atualize a lista com base na pesquisa
            List<CatalogoDeJogos> jogosFiltrados = allJogos.stream()
                    .filter(jogo -> jogo.getNome_jogo().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());

            jogosListView.getItems().clear();
            for (CatalogoDeJogos jogo : jogosFiltrados) {
                jogosListView.getItems().add("ID: " + jogo.getId_jogo() + " - Nome: " + jogo.getNome_jogo());
            }
        });

        editButton.setOnAction(e -> {
            // Verifica se há um item selecionado na lista
            if (!jogosListView.getSelectionModel().isEmpty()) {
                String selectedJogo = jogosListView.getSelectionModel().getSelectedItem();

                // Certifique-se de que selectedJogo não seja nulo
                if (selectedJogo != null) {
                    // Certifique-se de que extrairId() nunca retorna nulo
                    Integer id = extrairId(selectedJogo);

                    if (id != null) {
                        // Utilize Integer.equals para comparar Integer
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



        refreshButton.setOnAction(e -> {
            // Recarregue todos os jogos do banco de dados
            allJogos = catalogoDao.getCliCatalogo_de_Jogos();
            atualizarListaJogos();
        });

//        newJogoButton.setOnAction(e -> {
//            TelaCadastroJogo telaCadastroJogo = new TelaCadastroJogo();
//            telaCadastroJogo.start(new Stage());
//        });

        root.getChildren().addAll(titleLabel, searchField, jogosListView, editButton, removeButton, refreshButton, newJogoButton);

        stage.setScene(scene);
        stage.show();
    }

    private void atualizarListaJogos() {
        jogosListView.getItems().clear();
        for (CatalogoDeJogos jogo : allJogos) {
            jogosListView.getItems().add("ID: " + jogo.getId_jogo() + " - Nome: " + jogo.getNome_jogo());
        }
    }

    private int extrairId(String jogoString) {
        int idIndex = jogoString.indexOf("ID: ") + 4;
        int nomeIndex = jogoString.indexOf(" - Nome: ");
        String idStr = jogoString.substring(idIndex, nomeIndex);
        return Integer.parseInt(idStr);
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
            // Atualize os dados do jogo com base nos valores dos campos
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
