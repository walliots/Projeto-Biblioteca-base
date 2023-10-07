package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoVendas.domain.entity.Livro;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    Optional<Livro> findByNome(String nome);
}