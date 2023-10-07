package projetoVendas.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetoVendas.domain.entity.Prateleira;
import projetoVendas.domain.repository.PrateleiraRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/prateleiras")
public class PrateleirasController {

    @Autowired
    private PrateleiraRepository prateleiraRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Prateleira> getById(@PathVariable Integer id){
        return prateleiraRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<Prateleira>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(prateleiraRepository.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<Prateleira> save(@Valid @RequestBody Prateleira prateleira){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prateleiraRepository.save(prateleira));
    }

    @PutMapping
    public ResponseEntity<Prateleira> put(@Valid @RequestBody Prateleira prateleira){
        return prateleiraRepository.findById(prateleira.getId())
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(prateleiraRepository.save(prateleira)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        prateleiraRepository.findById(id).map(livro -> {
            prateleiraRepository.deleteById(id);
            return livro;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estante não encontrada"));
    }
}