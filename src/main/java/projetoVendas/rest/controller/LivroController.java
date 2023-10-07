package projetoVendas.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetoVendas.domain.entity.Livro;
import projetoVendas.domain.entity.Usuario;
import projetoVendas.domain.repository.LivroRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/livro")
public class LivroController {

    @Autowired
    private LivroRepository _livroRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Integer id){
        return _livroRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<Livro>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(_livroRepository.findByNome(nome));
    }

    @GetMapping("/listLivros")
    @ResponseStatus(HttpStatus.OK)
    public List<Livro> listUser(){
        return _livroRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Livro> save(@Valid @RequestBody Livro livro){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_livroRepository.save(livro));
    }

    @PutMapping
    public ResponseEntity<Livro> put(@Valid @RequestBody Livro livro){
        return _livroRepository.findById(livro.getId())
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(_livroRepository.save(livro)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        _livroRepository.findById(id).map(livro -> {
            _livroRepository.deleteById(id);
            return livro;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

}