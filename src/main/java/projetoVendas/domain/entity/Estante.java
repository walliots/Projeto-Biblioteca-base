package projetoVendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "estante")
@Entity
public class Estante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "nome")
    private String nome;

    @NotEmpty
    @Column(name = "categoria")
    private String categoria;

    @OneToMany
    private List<Estante> estantes;

}