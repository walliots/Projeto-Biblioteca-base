package projetoVendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoVendas.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //query methods
    Optional<Usuario>findByLogin(String login);
}
