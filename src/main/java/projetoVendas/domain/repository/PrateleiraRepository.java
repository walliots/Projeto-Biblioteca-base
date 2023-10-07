package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoVendas.domain.entity.Prateleira;

import java.util.Optional;

@Repository
public interface PrateleiraRepository extends JpaRepository<Prateleira, Integer> {

    Optional<Prateleira> findByNome(String nome);
}