package projetoVendas.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import projetoVendas.exception.PedidoNaoEncontradoException;
import projetoVendas.exception.RegraNegocioException;
import projetoVendas.rest.ApiErrors;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String menssagemErro = ex.getMessage();
        return new ApiErrors(menssagemErro);
    }
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException (MethodArgumentNotValidException ex){
       List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
