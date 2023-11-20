package Model;

import java.util.Date;

public class Pedidos {

    private String id_cliente;
    private String id_jogo;
    private String endereco;
    private double preco;
    private String plataforma;

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_jogo() {
        return id_jogo;
    }

    public void setId_jogo(String id_jogo) {
        this.id_jogo = id_jogo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }


    public void add(Clientes clientes2) {
        // TODO Auto-generated method stub

    }

}