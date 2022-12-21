package projetoVendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetoVendas.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private  Integer cliente;
    @NotNull(message = "{campo.total-cliente.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.itens-cliente.obrigatorio}")
    private List<ItemPedidoDTO> items;
}
