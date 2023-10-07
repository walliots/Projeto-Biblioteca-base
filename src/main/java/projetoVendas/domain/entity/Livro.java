package projetoVendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "livro")
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "nome", length = 100)
    private String nome;

    @NotEmpty
    @Column(name = "genero", length = 100)
    private String genero;

    @NotEmpty
    @Column(name = "editora", length = 100)
    private String editora;

    @Column(name = "status")
    private boolean status;
}