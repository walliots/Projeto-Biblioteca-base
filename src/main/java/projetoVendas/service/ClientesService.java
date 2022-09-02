package projetoVendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoVendas.model.Clientes;
import projetoVendas.repository.ClientesRepository;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository repository;


    public void  salvarClientes(Clientes cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void  validarCliente(Clientes cliente){

    }
}
