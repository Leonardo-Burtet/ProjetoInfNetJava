package org.infnet;
import org.infnet.exemplo.AssinaturaExemplo;
import org.infnet.factory.PagamentoFactory;
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
        //--------------------- Exemplo do uso do padrão Factory -------------
        /*
         Acredito que para o contexto da aplicação um padrão factory em pagamento pode ajudar na medida que a logica e as formas de pagamentos vão aumentando
         */
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("CD ACDC", "CD", BigDecimal.valueOf(10.00)));
        produtos.add(new Produto("CD ERIC CLAPTON", "CD", BigDecimal.valueOf(15.00)));

        LocalDate dataCompra = LocalDate.now();
        Cliente cliente = new Cliente("Leonardo Freitas");
        Assinatura assinatura = new Assinatura(dataCompra, AssinaturaEnum.SEMESTRAL, true);

        Pagamento pagamento = PagamentoFactory.criarPagamento(produtos, dataCompra, cliente, assinatura);
        System.out.println(pagamento.toString());
    }
}