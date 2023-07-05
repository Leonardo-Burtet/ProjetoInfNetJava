package org.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Assinatura {
    private BigDecimal mensalidade;

    private LocalDate begin;

    private Optional<LocalDate> end;

    private Cliente cliente;

    public Assinatura(BigDecimal mensalidade, LocalDate begin, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.cliente = cliente;
        this.end = Optional.empty();
    }

    public Assinatura(BigDecimal mensalidade, LocalDate begin, LocalDate end, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.of(end);
        this.cliente = cliente;
    }

    public long getTotalMesesAssinatura(LocalDate currentDate) {
        return ChronoUnit.MONTHS.between(this.getBegin(), currentDate);
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public Optional<LocalDate> getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = Optional.of(end);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
