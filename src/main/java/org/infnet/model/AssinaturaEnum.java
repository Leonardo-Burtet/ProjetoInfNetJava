package org.infnet.model;

import java.math.BigDecimal;

public enum AssinaturaEnum {

    TRIMESTRAL,
    SEMESTRAL,
    ANUAL;


    public static final BigDecimal TAXA_TRIMESTRAL = new BigDecimal(0.05);

    public static final BigDecimal TAXA_SEMESTRAL = new BigDecimal(0.03);

    public static final BigDecimal TAXA_ANUAL = new BigDecimal(0.00);

    public static final Long VALOR_TRIMESTRAL = 3L;

    public static final Long VALOR_SEMESTRAL = 6L;

    public static final Long VALOR_ANUAL = 12L;

    public static final BigDecimal ASSINATURA_TRIMESTRAL = new BigDecimal(100.00);

    public static final BigDecimal ASSINATURA_SEMESTRAL = new BigDecimal(300.00);

    public static final BigDecimal ASSINATURA_ANUAL = new BigDecimal(900.00);

    public BigDecimal getTaxaAssinatura() {
        switch (this){
            case TRIMESTRAL:
                return TAXA_TRIMESTRAL;
            case SEMESTRAL:
                return TAXA_SEMESTRAL;
            case ANUAL:
                return TAXA_ANUAL;
            default :
                throw new IllegalArgumentException("Tipo de Assinatura não Reconhecida");
        }
    }

    public Long getValorCongruenteTempoAssinatura(){
        switch (this){
            case TRIMESTRAL:
                return VALOR_TRIMESTRAL;
            case SEMESTRAL:
                return VALOR_SEMESTRAL;
            case ANUAL:
                return VALOR_ANUAL;
            default:
                throw new IllegalArgumentException("Tipo de Valor Congruente Assinatura não Reconhecido");
        }
    }

    public BigDecimal getValorDebitoAssinatura(){
        switch (this){
            case TRIMESTRAL:
                return ASSINATURA_TRIMESTRAL;
            case SEMESTRAL:
                return ASSINATURA_SEMESTRAL;
            case ANUAL:
                return ASSINATURA_ANUAL;
            default:
                throw new IllegalArgumentException("Tipo de Valor Débito Assinatura não Reconhecido");
        }
    }

}