package org.infnet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class Assinatura {
    private Long mensalidade;

    private AssinaturaEnum tipoAssinatura;

    private Boolean ativa;
    private LocalDate begin;

    private Optional<LocalDate> end;

    private Cliente cliente;

    public Assinatura(LocalDate begin, AssinaturaEnum tipoAssinatura, Boolean ativa) {
        this.begin = begin;
        this.end = Optional.empty();
        this.ativa = ativa;
        this.tipoAssinatura = tipoAssinatura;
        this.mensalidade = getTempoAssinaturaExecutada();
    }

    public Assinatura(){
    }

    public AssinaturaEnum getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(AssinaturaEnum tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
    public long getTotalMesesAssinatura(LocalDate currentDate) {
        return ChronoUnit.MONTHS.between(this.getBegin(), currentDate);
    }

    public Long getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Long mensalidade) {
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

    public Long getTempoAssinaturaExecutada(){
        return -ChronoUnit.MONTHS.between(LocalDate.now(), getBegin()) / tipoAssinatura.getValorCongruenteTempoAssinatura();
    }

    public BigDecimal getValorTotalMensalidade(){
        return tipoAssinatura.getValorDebitoAssinatura()
                .multiply(BigDecimal.valueOf(getTempoAssinaturaExecutada())).add(
                        tipoAssinatura.getTaxaAssinatura()
                                .multiply(tipoAssinatura.getValorDebitoAssinatura())
                                .multiply(BigDecimal.valueOf(getTempoAssinaturaExecutada())));
    }

    public BigDecimal getValorTotalDebitado(List<Assinatura> pagamentoAssinatura){
        return new BigDecimal(pagamentoAssinatura.stream().filter(Assinatura::getAtiva).map(assinatura -> assinatura.getTipoAssinatura().getValorDebitoAssinatura()).count()).add(
                        BigDecimal.valueOf(pagamentoAssinatura.stream().filter(Assinatura::getAtiva).count())
                                .multiply(tipoAssinatura.getTaxaAssinatura()))
                .multiply(tipoAssinatura.getValorDebitoAssinatura());
    }

    public StatusAssinaturaEnum getStatusAssinatura(List<Assinatura> pagamentoAssinatura){
        return getValorTotalMensalidade().compareTo(getValorTotalDebitado(pagamentoAssinatura)) == 0 ? StatusAssinaturaEnum.ATIVO : StatusAssinaturaEnum.PENDENTE;
    }

    public void validarExecucaoPagamentoProduto(List<Assinatura> pagamentoAssinatura){
        if (getStatusAssinatura(pagamentoAssinatura) == StatusAssinaturaEnum.PENDENTE){
            throw new IllegalArgumentException("Náo é Possível realizar Compra de Produtos - Assinatura Pendente");
        }
    }

}
