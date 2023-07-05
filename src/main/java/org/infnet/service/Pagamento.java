package org.infnet.service;

import org.infnet.model.Cliente;
import org.infnet.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Pagamento {

    private List<Produto> produtos;

    private LocalDate dataCompra;

    private Cliente cliente;

    public Pagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public BigDecimal getTotalValorProdutos() {
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
