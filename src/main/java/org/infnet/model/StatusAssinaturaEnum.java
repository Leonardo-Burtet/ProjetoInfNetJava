package org.infnet.model;

public enum StatusAssinaturaEnum {

    ATIVO ("Assinatura Ativa"),
    PENDENTE("Assinatura Em Atraso");

    private String descricao;

    private StatusAssinaturaEnum(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}