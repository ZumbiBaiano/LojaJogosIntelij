package Model;

import java.util.Date;

public class CatalogoDeJogos {

    private String id_jogo;
    private String nome_jogo;
    private double preco;
    private int classificacao_idade;
    private String plataforma;

    public String getId_jogo() {
        return id_jogo;
    }

    public void setId_jogo(String id_jogo) {
        this.id_jogo = id_jogo;
    }

    public String getNome_jogo() {
        return nome_jogo;
    }

    public void setNome_jogo(String nome_jogo) {
        this.nome_jogo = nome_jogo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getClassificacao_idade() {
        return classificacao_idade;
    }

    public void setClassificacao_idade(int classificacao_idade) {
        this.classificacao_idade = classificacao_idade;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }


}
