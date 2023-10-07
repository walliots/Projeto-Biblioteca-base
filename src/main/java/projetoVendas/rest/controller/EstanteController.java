package projetoVendas.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetoVendas.domain.entity.Estante;
import projetoVendas.domain.repository.EstanteRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/estante")
public class EstanteController {

    @Autowired
    private EstanteRepository _estanteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Estante> getById(@PathVariable Integer id){
        return _estanteRepository.findById(id).map( resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Optional<Estante>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(_estanteRepository.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<Estante> save(@Valid @RequestBody Estante estante){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(_estanteRepository.save(estante));
    }

    @PutMapping
    public ResponseEntity<Estante> put(@Valid @RequestBody Estante estante){
        return _estanteRepository.findById(estante.getId())
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(_estanteRepository.save(estante)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        _estanteRepository.findById(id).map(livro -> {
            _estanteRepository.deleteById(id);
            return livro;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estante não encontrada"));
    }
}