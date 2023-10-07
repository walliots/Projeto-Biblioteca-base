package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoVendas.domain.entity.Estante;

import java.util.Optional;

@Repository
public interface EstanteRepository extends JpaRepository<Estante, Integer>{

    Optional<Estante> findByNome(String nome);

}