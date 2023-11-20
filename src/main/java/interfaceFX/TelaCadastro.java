package interfaceFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaCadastro extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tela de Cadastro");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels
        Label nameLabel = new Label("Nome:");
        GridPane.setConstraints(nameLabel, 0, 0);

        Label emailLabel = new Label("E-mail:");
        GridPane.setConstraints(emailLabel, 0, 1);

        Label passwordLabel = new Label("Senha:");
        GridPane.setConstraints(passwordLabel, 0, 2);

        Label telefoneLabel = new Label("Telefone");
        GridPane.setConstraints(telefoneLabel,0,3); //mexe no texto

        Label cpfLabel = new Label("Cpf");
        GridPane.setConstraints(cpfLabel,0,4);

        // Campos de entrada
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 1);

        TextField telefoneinput = new TextField();
        GridPane.setConstraints(telefoneinput, 1, 3);


        TextField cpfinpunt = new TextField();
        GridPane.setConstraints(cpfinpunt, 1, 4);


        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 2);

        // Botão de cadastro
        Button registerButton = new Button("Cadastrar");
        GridPane.setConstraints(registerButton, 1, 6);
        registerButton.setOnAction(e -> {
            // Adicione a lógica de cadastro aqui
            System.out.println("usuario cadastro com sucesso");
        });

        grid.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, passwordLabel, passwordInput,
                registerButton,telefoneLabel,telefoneinput,cpfinpunt,cpfLabel);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
