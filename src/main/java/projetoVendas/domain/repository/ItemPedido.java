package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoVendas.domain.entity.ItensPedido;

public interface ItemPedido extends JpaRepository <ItensPedido, Integer> {
}
