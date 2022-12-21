package projetoVendas.rest.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetoVendas.domain.entity.Cliente;
import projetoVendas.domain.repository.Clientes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {
    @Autowired
    private Clientes clientes;


    @GetMapping("{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado pra o ID informado")
    })

    public Cliente getClienteById(@PathVariable @ApiParam("ID do cliente") Integer id){
        return  clientes.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @PostMapping
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 404, message = "Erro de validação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void delete (@PathVariable Integer id){
        //Metodo map. Precisa retornar
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        //Encadeando métodos
        //o map. Ele mapeia todos os dados so é preciso setar o ‘id’ por ser uma atualização
        //e se for preciso setar novas informações na classe não é preciso modificar o metodo abaixo
        clientes.findById(id).map(clienteExistente ->{
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return clienteExistente;
            //esse metodo recebe como parametro um suplier que é um objeto/ ‘interface’ funcional
            // que recebe um parametro e devolve
        } ).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        //recursos do spring data
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                //considera com ou sem case
                .withIgnoreCase()
                //forma que vai fazer para encontra os (valores) Strings
                //O CONTANING busca caracter independente da posição
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        //aqui o example pega o objeto e as propriedades que estão populadas
        //e vai criar um example(no 1 parametro)
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);

    }

}

