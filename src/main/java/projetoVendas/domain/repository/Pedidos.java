package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetoVendas.domain.entity.Cliente;
import projetoVendas.domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p left join fetch p.itemPedidos where p.id = :id")
    Optional<Pedido> findByIdFetchItemPedidos(@Param("id") Integer id);
}
