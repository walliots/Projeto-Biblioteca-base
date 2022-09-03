package projetoVendas.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Pedido {
    private Integer id;
    private  Cliente cliente;
    private LocalDate dataPedido;
    private BigDecimal total;
}
