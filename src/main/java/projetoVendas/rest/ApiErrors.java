package projetoVendas.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {
    @Getter
    public List<String> errors;

    public ApiErrors(String menssagemErro) {
        //essa classe arrays recebe um objeto e transforma em um arraylist
        this.errors = Arrays.asList(menssagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
