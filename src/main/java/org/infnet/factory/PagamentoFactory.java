package org.infnet.factory;

import org.infnet.model.Assinatura;
import org.infnet.model.Cliente;
import org.infnet.model.Produto;
import org.infnet.service.Pagamento;

import java.time.LocalDate;
import java.util.List;

public class PagamentoFactory {
    public static Pagamento criarPagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente, Assinatura assinatura) {
        return new Pagamento(produtos, dataCompra, cliente, assinatura);
    }
}
