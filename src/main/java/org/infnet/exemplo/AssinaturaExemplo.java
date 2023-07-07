package org.infnet.exemplo;

import org.infnet.model.Assinatura;
import org.infnet.model.AssinaturaEnum;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class AssinaturaExemplo {

    public ArrayList<Assinatura>  getAssinaturaExemplo(){
        Assinatura assinaturaJonaya = new Assinatura(LocalDate.now().minusMonths(4), AssinaturaEnum.TRIMESTRAL, true);
        Assinatura assinaturaJulio = new Assinatura(LocalDate.of(2023,Month.JANUARY, 1), AssinaturaEnum.SEMESTRAL, true);
        Assinatura assinaturaVitoria = new Assinatura(LocalDate.of(2022,Month.JANUARY, 1),AssinaturaEnum.ANUAL, false);

        ArrayList<Assinatura> assinatura = new ArrayList<>();
        assinatura.add(assinaturaJonaya);
        assinatura.add(assinaturaJulio);
        assinatura.add(assinaturaVitoria);

        return assinatura;
    }

    public ArrayList<ArrayList<Assinatura>>  getAssinaturaComplementoExemplo(){

        ArrayList<Assinatura> assinatura = getAssinaturaExemplo();

        ArrayList<ArrayList<Assinatura>> assinaturaParcelaCliente = new ArrayList<>();

        assinatura.forEach(construcaoNumeroMensalidade ->{
            ArrayList<Assinatura> assinaturaTemp = new ArrayList<>();
            long mensalidade = construcaoNumeroMensalidade.getMensalidade();

            for (long i = 0; i < mensalidade; i++){
                assinaturaTemp.add(construcaoNumeroMensalidade);
            }

            assinaturaParcelaCliente.add(assinaturaTemp);
        });

        return assinaturaParcelaCliente;
    }

}