package org.infnet;

import org.infnet.model.Assinatura;
import org.infnet.model.Cliente;
import org.infnet.model.Produto;
import org.infnet.service.Pagamento;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        Produto produto1 = new Produto("Sample R&B", "music/sample/r&b/01", new BigDecimal("150.00"));
        Produto produto2 = new Produto("Video institucional generico", "video/institucional/01", new BigDecimal("350.00"));
        Produto produto3 = new Produto("Capa generica para album", "img/capa/01", new BigDecimal("100.00"));

        Cliente cliente1 = new Cliente("Leonardo");
        Cliente cliente2 = new Cliente("Bruno");
        Cliente cliente3 = new Cliente("Jessica");

        List<Produto> carrinhoDeCompras = new ArrayList<>();
        carrinhoDeCompras.add(produto1);
        carrinhoDeCompras.add(produto2);
        carrinhoDeCompras.add(produto3);

        List<Produto> carrinhoDeCompras2 = new ArrayList<>();
        carrinhoDeCompras2.add(produto1);
        carrinhoDeCompras2.add(produto3);
        carrinhoDeCompras2.add(produto3);

        List<Produto> carrinhoDeCompras3 = new ArrayList<>();
        carrinhoDeCompras3.add(produto1);
        carrinhoDeCompras3.add(produto1);
        carrinhoDeCompras3.add(produto1);

        LocalDate currentDate = LocalDate.now();

//        1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos.
//        Crie Pagamentos com:  a data de hoje, ontem e um do mês passado.
        Pagamento pagamentoCliente1 = new Pagamento(carrinhoDeCompras, currentDate, cliente1);
        Pagamento pagamentoCliente2 = new Pagamento(carrinhoDeCompras2, currentDate.minusDays(1), cliente2);
        Pagamento pagamentoCliente3 = new Pagamento(carrinhoDeCompras3, currentDate.minusMonths(1), cliente3);

//        2 - Ordene e imprima os pagamentos pela data de compra.
        System.out.println("--------------------------");
         List<Pagamento> listaDeCompras= new ArrayList<>();
         listaDeCompras.add(pagamentoCliente1);
         listaDeCompras.add(pagamentoCliente2);
         listaDeCompras.add(pagamentoCliente3);

        for (Pagamento elemento : listaDeCompras) {
            System.out.println(elemento.getDataCompra());
        }
         listaDeCompras.sort(Comparator.comparing(Pagamento::getDataCompra));

        System.out.println("Lista ordenada");
        for (Pagamento elemento : listaDeCompras) {
            System.out.println(elemento.getDataCompra());
        }
//        3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.
        System.out.println("--------------------------");
        Optional<Double> pagamentoUnitario = Optional.of(carrinhoDeCompras3.stream().mapToDouble(p -> p.getPreco().doubleValue()).sum());
        System.out.println(pagamentoUnitario.get() + " compra");
//        4 -  Calcule o Valor de todos os pagamentos da Lista de pagamentos.
        System.out.println("--------------------------");
        List<List<Produto>> paymentProducts = listaDeCompras.stream().map(Pagamento::getProdutos).collect(Collectors.toList());
        paymentProducts.stream().forEach(p -> {
            OptionalDouble sumOpt = OptionalDouble.of(p.stream().mapToDouble(pro -> pro.getPreco().doubleValue()).reduce(0.0, (a, b) -> a + b));
            if (sumOpt.isPresent())
                System.out.println("SOMA DOS PRODUTOS: " + sumOpt.getAsDouble());
        });
        List<Double> somaPagamentos = new ArrayList<>();
        paymentProducts.stream().forEach(produto -> {
            somaPagamentos.add(produto.stream().mapToDouble(unidade -> unidade.getPreco().doubleValue()).reduce(0.0, (a, b) -> a +b));
        });
        System.out.println("SOMA TOTAL PRODUTOS " + somaPagamentos.stream().reduce(0.0, (a, b) -> a + b));
//        5 - Imprima a quantidade de cada Produto vendido.
        System.out.println("--------------------------");
        List<Produto> todosProdutos = Arrays.asList(produto1, produto2, produto3);
        List<Produto> produtosVendidos = new ArrayList<>();
        paymentProducts.stream().forEach(produtosVendidos::addAll);

        todosProdutos.stream().forEach(produto -> {
            System.out.println("Quantidade vendida do produto " + produto.getNome() + " : " + produtosVendidos.stream().filter(ps -> ps.equals(produto)).count());

        });
//        6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente.
        System.out.println("--------------------------");
        Map<String, List<String>> listaClienteProduto = new HashMap<>();
        listaDeCompras.forEach(compra -> {
            String clienteNome = compra.getCliente().getNome();
            List<String> produtosDoCliente = compra.getProdutos()
                    .stream()
                    .map(Produto::getNome)
                    .collect(Collectors.toList());
            listaClienteProduto.put(clienteNome, produtosDoCliente);
        });

        System.out.println(listaClienteProduto);


//        7 - Qual cliente gastou mais?
        System.out.println("--------------------------");
        Map<String, BigDecimal> listaClienteGastos = new HashMap<>();

        listaDeCompras.forEach(compra -> {
            String clienteNome = compra.getCliente().getNome();
            BigDecimal produtosDoCliente = compra.getProdutos()
                    .stream()
                    .map(Produto::getPreco)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            listaClienteGastos.put(clienteNome, produtosDoCliente);
        });

        System.out.println(listaClienteGastos);

        Map.Entry<String, BigDecimal> clienteQueGastouMais = listaClienteGastos.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        String clienteNomeGastouMais = clienteQueGastouMais.getKey();
        BigDecimal maiorGasto = clienteQueGastouMais.getValue();

        System.out.println(clienteNomeGastouMais + ":" + maiorGasto);

//        8 - Quanto foi faturado em um determinado mês?
        System.out.println("--------------------------");
        List<Pagamento> junePayments = listaDeCompras.stream().filter(k -> k.getDataCompra().getMonth().equals(Month.JULY)).collect(Collectors.toList());
        List<Produto> allProductSoldInJune = new ArrayList<>();
        junePayments.stream().map( pay -> pay.getProdutos()).forEach(allProductSoldInJune::addAll);
        BigDecimal sum = allProductSoldInJune.stream().map(p -> p.getPreco()).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Valor faturado em Junho: " + sum.toString());

//        9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.
        Assinatura assinatura1 = new Assinatura(new BigDecimal(99.98), currentDate.minusMonths(10), cliente1 );
        Assinatura assinatura2 = new Assinatura(new BigDecimal(99.98), currentDate.minusMonths(3),currentDate, cliente2 );
        Assinatura assinatura3 = new Assinatura(new BigDecimal(99.98)  , currentDate.minusMonths(5),currentDate, cliente3 );
//        10 - Imprima o tempo em meses de alguma assinatura ainda ativa.
        System.out.println("--------------------------");
        System.out.println(assinatura1.getTotalMesesAssinatura(currentDate));
//        11 - Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time.
        System.out.println("--------------------------");
        List<Assinatura> assinaturas = Arrays.asList(assinatura1, assinatura2, assinatura3);
        assinaturas.stream().forEach(s -> {
            Period periodo2 = Period.between(s.getBegin(), s.getEnd().orElse(LocalDate.from(currentDate)));
            System.out.println("Tempo em meses de assinatura entre start e end da assinatura do  " + s.getCliente().getNome() + " : " + periodo2.getMonths());
        });

//        12 - Calcule o valor pago em cada assinatura até o momento.
        System.out.println("--------------------------");
        assinaturas.forEach(assinaturaAtiva -> {
            long mesesAtivo = ChronoUnit.MONTHS.between(assinaturaAtiva.getEnd().orElse(currentDate), assinaturaAtiva.getBegin());
            System.out.println("Meses da Assinatura " + (assinaturas.indexOf(assinaturaAtiva) + 1) + " Pago: R$ " + (new BigDecimal(-mesesAtivo).multiply(assinaturaAtiva.getMensalidade())));
        });
    }
}