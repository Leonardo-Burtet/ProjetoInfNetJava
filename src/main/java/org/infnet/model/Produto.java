package org.infnet.model;

import org.infnet.service.Pagamento;

import java.math.BigDecimal;

public class Produto {
    private String nome;
    private String file;
    private BigDecimal preco;

    public Produto(String nome, String file, BigDecimal preco) {
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
