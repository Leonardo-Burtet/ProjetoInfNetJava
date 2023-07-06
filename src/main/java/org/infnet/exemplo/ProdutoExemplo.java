package org.infnet.exemplo;


import org.infnet.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoExemplo {

    public ArrayList<Produto> getProdutoExemplo(){
        Produto produto1 = new Produto("Kit Fluídos Isqueiro","teste", new BigDecimal("100.00"));
        Produto produto2  = new Produto("Cigarreira","teste", new BigDecimal("200.00"));

        List<Produto> venda1 = new ArrayList<>();
        venda1.add(produto1);
        venda1.add(produto2);

        return (ArrayList<Produto>) venda1;
    }
}