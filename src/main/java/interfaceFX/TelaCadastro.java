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
import Model.Clientes;
import DAO.ClientesDAO;

public class TelaCadastro extends Application {

    private ClientesDAO clientesDAO;
    private TextField nameInput;
    private TextField emailInput;
    private PasswordField passwordInput;
    private TextField telefoneinput;
    private TextField cpfinpunt;
    private TextField enderecoInput; // Adicione o campo de endereço

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tela de Cadastro");

        clientesDAO = new ClientesDAO();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Nome:");
        GridPane.setConstraints(nameLabel, 0, 0);

        Label emailLabel = new Label("E-mail:");
        GridPane.setConstraints(emailLabel, 0, 1);

        Label passwordLabel = new Label("Senha:");
        GridPane.setConstraints(passwordLabel, 0, 2);

        Label telefoneLabel = new Label("Telefone:");
        GridPane.setConstraints(telefoneLabel, 0, 3);

        Label cpfLabel = new Label("CPF:");
        GridPane.setConstraints(cpfLabel, 0, 4);

        Label enderecoLabel = new Label("Endereço:"); // Novo Label para Endereço
        GridPane.setConstraints(enderecoLabel, 0, 5);

        nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 1);

        passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 2);

        telefoneinput = new TextField();
        GridPane.setConstraints(telefoneinput, 1, 3);

        cpfinpunt = new TextField();
        GridPane.setConstraints(cpfinpunt, 1, 4);

        enderecoInput = new TextField(); // Novo campo de endereço
        GridPane.setConstraints(enderecoInput, 1, 5);

        Button registerButton = new Button("Cadastrar");
        GridPane.setConstraints(registerButton, 1, 6);
        registerButton.setOnAction(e -> {
            cadastrarUsuario(
                    nameInput.getText(),
                    emailInput.getText(),
                    passwordInput.getText(),
                    telefoneinput.getText(),
                    cpfinpunt.getText(),
                    enderecoInput.getText()); // Adicione o endereço
            clearFields(); // Limpe os campos após o cadastro
        });

        grid.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, passwordLabel, passwordInput,
                registerButton, telefoneLabel, telefoneinput, cpfinpunt, cpfLabel,
                enderecoLabel, enderecoInput); // Adicione o campo de endereço

        Scene scene = new Scene(grid, 300, 300); // Ajuste a altura para exibir todos os campos
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void cadastrarUsuario(String nome, String email, String senha, String telefone, String cpf, String endereco) {
        Clientes cliente = new Clientes();
        cliente.setNome_cliente(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco); // Adicione o endereço

        clientesDAO.save(cliente);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void clearFields() {
        nameInput.clear();
        emailInput.clear();
        passwordInput.clear();
        telefoneinput.clear();
        cpfinpunt.clear();
        enderecoInput.clear();
    }
}
