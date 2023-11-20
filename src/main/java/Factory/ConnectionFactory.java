package Factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //Nome do Usuário do MySQL
    private static final String USERNAME = "root";


    //Senha do banco
    private static final String PASSWORD = "123456";

    //Caminho do Banco de dados, porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Game_Shop";

    //Coneção com o banco de dados
    public static Connection createConnectionToMySQL() throws Exception{
        //Faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Cria a conecxão com Banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception {

        //Recuperar uma conecxão com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testar se a conexão é nula
        if(con != null) {
            System.out.println("Conecxão obtida com sucesso!");
            con.close();
        }
    }

}
