package org.infnet.exemplo;

import org.infnet.model.Cliente;
import java.util.ArrayList;

public class ClienteExemplo {
    public ArrayList<Cliente>  getClienteExemplo(){
        ArrayList<Cliente> cliente = new ArrayList<Cliente>();
        cliente.add(new Cliente("Jonaya"));
        cliente.add(new Cliente("Júlio"));
        cliente.add(new Cliente("Vitoria"));

        return cliente;
    }
}