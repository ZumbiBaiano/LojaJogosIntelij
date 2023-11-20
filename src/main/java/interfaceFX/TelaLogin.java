package interfaceFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class TelaLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tela de Login");

        VBox root = new VBox(10);
        Scene scene = new Scene(root, 300, 150);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels
        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 0);

        Label passwordLabel = new Label("Senha:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        // Campos de entrada
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 0);

        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // Botão de login
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 3);
        loginButton.setOnAction(e -> realizarLogin(emailInput.getText(), passwordInput.getText()));

        // Adicione essas linhas para criar elementos chamados TerrariaLabel e Terraria
        Label TerrariaLabel = new Label("Terraria Label");
        TextField Terraria = new TextField();

        root.getChildren().addAll(emailLabel, emailInput, passwordLabel, passwordInput, loginButton, TerrariaLabel, Terraria);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void realizarLogin(String email, String senha) {
        // Adicione aqui a lógica para verificar o login com o email e senha fornecidos.
        // Por enquanto, apenas exibiremos as informações no console.
        System.out.println("Email: " + email);
        System.out.println("Senha criptografada: " + criptografarSenha(senha));
    }

    private String criptografarSenha(String senha) {
        // Use SHA-256 para calcular o hash da senha
        return DigestUtils.sha256Hex(senha);
    }

    public static void main(String[] args) {
        launch(args);
    }
}