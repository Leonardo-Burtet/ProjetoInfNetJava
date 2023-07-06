package org.infnet;
import org.infnet.exemplo.AssinaturaExemplo;
import org.infnet.model.Assinatura;
import org.infnet.model.AssinaturaEnum;
import org.infnet.model.Cliente;
import org.infnet.model.Produto;
import org.infnet.service.Pagamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //--------------------- Entrada de dados -------------
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("CD ACDC", "CD", BigDecimal.valueOf(10.00)));
        produtos.add(new Produto("CD ERIC CLAPTON", "CD", BigDecimal.valueOf(15.00)));
        ArrayList<Pagamento> pagamento = new ArrayList<>();
        pagamento.add(new Pagamento(produtos, LocalDate.now(), new Cliente("Leo"), new Assinatura(LocalDate.now().minusMonths(12), AssinaturaEnum.ANUAL, true)));
        pagamento.add(new Pagamento(produtos, LocalDate.now(), new Cliente("Leonardo"), new Assinatura(LocalDate.now().minusMonths(7), AssinaturaEnum.TRIMESTRAL, true)));
        pagamento.add(new Pagamento(produtos, LocalDate.now(), new Cliente("Lucas"), new Assinatura(LocalDate.now().minusMonths(7), AssinaturaEnum.SEMESTRAL, false)));
        AssinaturaExemplo assinaturaExemplo = new AssinaturaExemplo();
        ArrayList<ArrayList<Assinatura>> assinaturaComplementoExemplo = assinaturaExemplo.getAssinaturaComplementoExemplo();

        System.out.println("-------------------------------------------------\n");
        pagamento.forEach(testeMecanismos -> {
            System.out.println("Cliente: " + testeMecanismos.getCliente().getNome() + " - Total de Renovaçãos: " + testeMecanismos.getTempoAssinaturaExecutada() + " - Tipo de mensalidade: " + testeMecanismos.getTipoAssinatura().toString());
        });
        System.out.println("-------------------------------------------------\n");

        pagamento.forEach(testeMecanismos -> {
            System.out.println("Cliente: " + testeMecanismos.getCliente().getNome() + " - Débito até o momento: " + testeMecanismos.getValorTotalDebitado(assinaturaComplementoExemplo.get(pagamento.indexOf(testeMecanismos))) + " - Tipo de mensalidade: " + testeMecanismos.getTipoAssinatura().toString());
        });
        System.out.println("-------------------------------------------------\n");

        pagamento.forEach(testeMecanismos -> {
            System.out.println("Cliente: " + testeMecanismos.getCliente().getNome() + " - Valor Total para de todas as parcelas que devem ser pagas: " + testeMecanismos.getValorTotalMensalidade() + " - Tipo de mensalidade: " + testeMecanismos.getTipoAssinatura().toString());
        });
        System.out.println("-------------------------------------------------\n");

        pagamento.forEach(testeMecanismos -> {
            System.out.println("Cliente: " + testeMecanismos.getCliente().getNome() + " - Status da Assinatura: " + testeMecanismos.getStatusAssinatura(assinaturaComplementoExemplo.get(pagamento.indexOf(testeMecanismos))) + " - Tipo de mensalidade: " + testeMecanismos.getTipoAssinatura().toString());
        });
        System.out.println("----------------------------00---------------------\n");

        pagamento.forEach(testeMecanismos -> {
            System.out.println(testeMecanismos.getCliente().getNome() + " - Status da Assinatura: " + testeMecanismos.getStatusAssinatura(assinaturaComplementoExemplo.get(pagamento.indexOf(testeMecanismos))) + " - Tipo de mensalidade: " + testeMecanismos.getTipoAssinatura().toString());
            Produto produto2  = new Produto("Cigarreira","cd", new BigDecimal("200.00"));
            System.out.println("adicionando produto para: " + testeMecanismos.getCliente().getNome() + "\n");
            testeMecanismos.validarExecucaoPagamentoProduto(assinaturaComplementoExemplo.get(pagamento.indexOf(testeMecanismos)));
            testeMecanismos.getProdutos().add(produto2);
        });
        System.out.println("-------------------------------------------------\n");
    }
}