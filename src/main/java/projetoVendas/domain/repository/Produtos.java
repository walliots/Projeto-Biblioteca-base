package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoVendas.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
